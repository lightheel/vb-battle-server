package com.example.restservice;

import com.example.vb_battle_server.Character;
import com.example.restservice.security.NacatechAuthenticationToken;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


class combatRoundWrapper{
    int playerCombatDamage;
    int opponentCombatDamage;
    boolean playerAttackHit;
}
@RestController
@Validated
public class PVPController {

    private static final Logger logger = LoggerFactory.getLogger(PVPController.class);
    private static final long COMBAT_TIMEOUT_MS = 300_000; // 5 minutes timeout for combat sessions
    private static final int MAX_COMBAT_SESSIONS = 1000; // Maximum concurrent combat sessions
    
    int msgID = 0;
    SecureRandom rand = new SecureRandom();
    
    // Use ConcurrentHashMap instead of Hashtable for better thread safety
    private final ConcurrentHashMap<String, pvpInstance> combatDictionary = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> combatTimestamps = new ConcurrentHashMap<>();
    private final ScheduledExecutorService cleanupScheduler = Executors.newScheduledThreadPool(1);
    
    public PVPController() {
        // Schedule periodic cleanup of stale combat sessions
        cleanupScheduler.scheduleAtFixedRate(this::cleanupStaleCombats, 1, 1, TimeUnit.MINUTES);
    }
    
    /**
     * Cleanup stale combat sessions that haven't been accessed in the timeout period
     */
    private void cleanupStaleCombats() {
        long now = System.currentTimeMillis();
        combatTimestamps.entrySet().removeIf(entry -> {
            if (now - entry.getValue() > COMBAT_TIMEOUT_MS) {
                combatDictionary.remove(entry.getKey());
                logger.debug("Cleaned up stale combat session for player: {}", entry.getKey());
                return true;
            }
            return false;
        });
        
        // Prevent memory exhaustion by limiting dictionary size
        if (combatDictionary.size() > MAX_COMBAT_SESSIONS) {
            logger.warn("Combat dictionary size exceeded limit: {}. Cleaning up oldest entries.", combatDictionary.size());
            // Remove oldest entries
            combatTimestamps.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .limit(combatDictionary.size() - MAX_COMBAT_SESSIONS + 100)
                .forEach(entry -> {
                    combatDictionary.remove(entry.getKey());
                    combatTimestamps.remove(entry.getKey());
                });
        }
    }

    //Calculation used to check if attack hits.
    boolean checkAttackHit(Character digi1, Character digi2) {
        // Prevent division by zero
        float totalBp = digi1.getBaseBp() + digi2.getBaseBp();
        if (totalBp <= 0) {
            // If both have 0 BP, default to 50% hit rate
            logger.debug("Hitrate check: Both have 0 BP, defaulting to 50%");
            return rand.nextInt(100) < 50;
        }
        
        // Calculate base hitrate: your DP / (your DP + enemy DP) * 100
        float baseHitrate = (digi1.getBaseBp() / totalBp * 100);
        
        // Apply attribute modifier
        int attributeModifier = getAttributeModifier(digi1.getAttribute(), digi2.getAttribute());
        float hitrate = baseHitrate + attributeModifier;
        
        // Clamp hitrate between 0 and 100
        if (hitrate < 0) hitrate = 0;
        if (hitrate > 100) hitrate = 100;
        
        int rand_int = rand.nextInt(100); // Fixed: was rand.nextInt(99), now allows 100% hitrate
        boolean hit = rand_int < hitrate;
        
        // Debug logging
        logger.debug("Hitrate check: {} (attr={}) vs {} (attr={}) | BaseBP: {}/{} | BaseHitrate: {}% | AttrMod: {} | FinalHitrate: {}% | Roll: {} | Result: {}",
            digi1.getName(), digi1.getAttribute(),
            digi2.getName(), digi2.getAttribute(),
            digi1.getBaseBp(), digi2.getBaseBp(),
            String.format("%.2f", baseHitrate), attributeModifier, String.format("%.2f", hitrate), rand_int, hit ? "HIT" : "MISS");
        
        return hit;
    }

    /**
     * Get attribute modifier based on attribute matchup chart.
     * Attributes: 1=Vaccine, 2=Virus, 3=Data, 4=Free
     * Returns modifier to add to hitrate (+5, 0, or -5)
     */
    private int getAttributeModifier(int yourAttribute, int enemyAttribute) {
        // Free attribute is always neutral
        if (yourAttribute == 4 || enemyAttribute == 4) {
            return 0;
        }
        
        // Same attribute = no modifier
        if (yourAttribute == enemyAttribute) {
            return 0;
        }
        
        // Vaccine (1) vs Virus (2): +5
        // Vaccine (1) vs Data (3): -5
        if (yourAttribute == 1) {
            if (enemyAttribute == 2) return 5;  // Vaccine beats Virus
            if (enemyAttribute == 3) return -5;  // Vaccine loses to Data
        }
        
        // Virus (2) vs Vaccine (1): -5
        // Virus (2) vs Data (3): +5
        if (yourAttribute == 2) {
            if (enemyAttribute == 1) return -5; // Virus loses to Vaccine
            if (enemyAttribute == 3) return 5;   // Virus beats Data
        }
        
        // Data (3) vs Vaccine (1): +5
        // Data (3) vs Virus (2): -5
        if (yourAttribute == 3) {
            if (enemyAttribute == 1) return 5;  // Data beats Vaccine
            if (enemyAttribute == 2) return -5; // Data loses to Virus
        }
        
        return 0; // Default (shouldn't reach here)
    }

    //Combat instance class
    class pvpInstance {
        Character winner;
        int combatRound = 0;
        String playerID;
        Character playerCharacter;
        Character opponentCharacter;
        int playerMaxHp; // Player's max HP including vitality bonus

        public pvpInstance(String inputPlayerID, Character inputPlayerCharacter, Character inputOpponentCharacter, int inputPlayerMaxHp) {
            playerID = inputPlayerID;
            playerCharacter = inputPlayerCharacter;
            opponentCharacter = inputOpponentCharacter;
            playerMaxHp = inputPlayerMaxHp;
        }

        boolean isCombatComplete() {
            if (playerCharacter.getCurrentHp() <= 0) {
                setWinner(opponentCharacter);
                return true;
            }
            else if (opponentCharacter.getCurrentHp() <= 0) {
                setWinner(playerCharacter);
                return true;
            }
            else {
                return false;
            }
        }

        void setWinner(Character matchWinner) {
            winner = matchWinner;
        }
        Character getWinner() {
            return winner;
        }

        combatRoundWrapper processCombatRound(float critBar) {
            combatRound++;
            combatRoundWrapper tempRoundWrapper = new combatRoundWrapper();

            int attackDamage = 0;
            
            // Reduced variability: random multiplier between 0.9 and 1.1 (10% variance)
            float randomMultiplier = 0.9f + (rand.nextFloat() * 0.2f); // 0.9 to 1.1

            if (checkAttackHit(playerCharacter, opponentCharacter)) {
                // Player hits opponent - use critBar for crit calculation
                float baseDamage = playerCharacter.getBaseAp() * randomMultiplier;
                
                if (critBar == 100.0f) {
                    // Critical hit: 1.3x damage
                    attackDamage = (int)(baseDamage * 1.3f);
                    tempRoundWrapper.playerCombatDamage = attackDamage;
                }
                else {
                    attackDamage = (int)baseDamage;
                    tempRoundWrapper.playerCombatDamage = attackDamage;
                }
                //deal damage to enemy
                opponentCharacter.setCurrentHp(opponentCharacter.getCurrentHp() - attackDamage);
                //System.out.println("opponent HP: " + opponentCharacter.getCurrentHp());
                tempRoundWrapper.playerAttackHit = true;
                return tempRoundWrapper;
            }
            else {
                // Opponent hits player - no critBar (opponent doesn't use player's critBar)
                float baseDamage = opponentCharacter.getBaseAp() * randomMultiplier;
                attackDamage = (int)baseDamage;
                tempRoundWrapper.opponentCombatDamage = attackDamage;
                //deal damage to player
                playerCharacter.setCurrentHp(playerCharacter.getCurrentHp() - attackDamage);
                //System.out.println("player HP: " + playerCharacter.getCurrentHp());
                tempRoundWrapper.playerAttackHit = false;
                return tempRoundWrapper;
            }
        }

        void resetCombat() {
            playerCharacter.setCurrentHp(playerCharacter.getBaseHp());
            opponentCharacter.setCurrentHp(opponentCharacter.getBaseHp());
            combatRound = 0;
        }
    }

    Character findPlayerDigi(int stage, String name) {
        // Trim whitespace that might cause lookup failures
        if (name != null) {
            name = name.trim();
        }
        
        logger.debug("Searching for player char: stage={}, name='{}'", stage, name);
        
        // Validate input to prevent injection attacks
        if (name == null || name.isEmpty() || name.length() > 100) {
            logger.warn("Invalid character name provided: {}", name);
            return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
        }

        if (stage == 0) {
            for (int i = 0; i < VBRookieStats.rookieArray.size(); i++) {
                // Fixed: Use equals() instead of matches() to prevent regex injection
                if (name.equals(VBRookieStats.rookieArray.get(i).getCharaId())) {
                    return VBRookieStats.rookieArray.get(i);
                }
            }
        }
        else if (stage == 1) {
            for (int i = 0; i < VBChampionStats.championArray.size(); i++) {
                if (name.equals(VBChampionStats.championArray.get(i).getCharaId())) {
                    return VBChampionStats.championArray.get(i);
                }
            }
        }
        else if (stage == 2) {
            for (int i = 0; i < VBUltimateStats.ultimateArray.size(); i++) {
                if (name.equals(VBUltimateStats.ultimateArray.get(i).getCharaId())) {
                    return VBUltimateStats.ultimateArray.get(i);
                }
            }
        }
        else if (stage == 3) {
            for (int i = 0; i < VBMegaStats.megaArray.size(); i++) {
                if (name.equals(VBMegaStats.megaArray.get(i).getCharaId())) {
                    return VBMegaStats.megaArray.get(i);
                }
            }
        }

        logger.warn("Character not found: stage={}, name={}", stage, name);
        return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
    }

    Character findOpponentDigi(int stage, String name){
        // Trim whitespace that might cause lookup failures
        if (name != null) {
            name = name.trim();
        }
        
        logger.debug("Searching for opponent char: stage={}, name='{}'", stage, name);
        
        // Validate input to prevent injection attacks
        if (name == null || name.isEmpty() || name.length() > 100) {
            logger.warn("Invalid character name provided: {}", name);
            return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
        }

        if (stage == 0) {
            for (int i = 0; i < VBRookieStats.rookieArray.size(); i++) {
                // Fixed: Use equals() instead of matches() to prevent regex injection
                if (name.equals(VBRookieStats.rookieArray.get(i).getCharaId())) {
                    return VBRookieStats.rookieArray.get(i);
                }
            }
        }
        else if (stage == 1) {
            for (int i = 0; i < VBChampionStats.championArray.size(); i++) {
                if (name.equals(VBChampionStats.championArray.get(i).getCharaId())) {
                    return VBChampionStats.championArray.get(i);
                }
            }
        }
        else if (stage == 2) {
            for (int i = 0; i < VBUltimateStats.ultimateArray.size(); i++) {
                if (name.equals(VBUltimateStats.ultimateArray.get(i).getCharaId())) {
                    return VBUltimateStats.ultimateArray.get(i);
                }
            }
        }
        else if (stage == 3) {
            for (int i = 0; i < VBMegaStats.megaArray.size(); i++) {
                if (name.equals(VBMegaStats.megaArray.get(i).getCharaId())) {
                    return VBMegaStats.megaArray.get(i);
                }
            }
        }

        logger.warn("Character not found: stage={}, name={}", stage, name);
        return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
    }

    boolean checkExistingCombat(String playerID, Character playerCharacter, Character opponentCharacter, int playerMaxHp) {
        // Validate playerID
        if (playerID == null || playerID.isEmpty() || playerID.length() > 200) {
            logger.warn("Invalid playerID provided: {}", playerID);
            return false;
        }
        
        // Adds combat if player ID isn't already in a match.
        pvpInstance existing = combatDictionary.putIfAbsent(playerID, 
            new pvpInstance(playerID, playerCharacter, opponentCharacter, playerMaxHp));
        
        if (existing == null) {
            // New combat session created
            combatTimestamps.put(playerID, System.currentTimeMillis());
            return true;
        } else {
            // Player ID already in active match
            return false;
        }
    }
    
    /**
     * Get authenticated user ID from security context
     */
    private String getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof NacatechAuthenticationToken nacatechAuth) {
            return nacatechAuth.getUserInfo().userId();
        }
        return null;
    }

    /**
     * Calculate HP bonus based on vital points and stage.
     * Max vital: Rookie=2500, Champion=5000, Ultimate=7500, Mega=9999
     * HP bonus tiers: 0-25%=0%, 25-50%=15%, 50-75%=30%, 75-100%=45%
     * Returns the HP bonus multiplier (e.g., 1.15 for 15% bonus)
     */
    private float calculateHpBonusMultiplier(int stage, int vitalPoints) {
        int maxVitality;
        switch (stage) {
            case 0: // Rookie
                maxVitality = 2500;
                break;
            case 1: // Champion
                maxVitality = 5000;
                break;
            case 2: // Ultimate
                maxVitality = 7500;
                break;
            case 3: // Mega
                maxVitality = 9999;
                break;
            default:
                maxVitality = 2500; // Default to Rookie
        }
        
        if (maxVitality <= 0) {
            return 1.0f; // No bonus if max vitality is invalid
        }
        
        float vitalityPercentage = (float) vitalPoints / maxVitality * 100;
        
        if (vitalityPercentage < 25.0f) {
            return 1.0f; // 0% bonus
        } else if (vitalityPercentage < 50.0f) {
            return 1.15f; // 15% bonus
        } else if (vitalityPercentage < 75.0f) {
            return 1.30f; // 30% bonus
        } else {
            return 1.45f; // 45% bonus
        }
    }
    @GetMapping("api/pvp")
    public PVP pvp(
            @RequestParam(value = "apiStage") @Min(0) @Max(2) int apiStage,
            @RequestParam(value = "playerID") @NotBlank String playerID,
            @RequestParam(value = "playerDigi") @NotBlank String playerDigi,
            @RequestParam(value = "playerStage") @Min(0) @Max(3) int playerStage,
            @RequestParam(value = "critBar") @Min(0) @Max(100) float critBar,
            @RequestParam(value = "opponentDigi") @NotBlank String opponentDigi,
            @RequestParam(value = "opponentStage") @Min(0) @Max(3) int opponentStage,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "vitalPoints", required = false) Integer vitalPoints) {

        // Optional: Verify authenticated user matches playerID if token was provided
        // This allows the endpoint to work with just playerID (original behavior)
        // but still validates if a token is present
        String authenticatedUserId = getAuthenticatedUserId();
        if (authenticatedUserId != null && !authenticatedUserId.equals(playerID)) {
            logger.warn("User ID mismatch: authenticated={}, requested={}", authenticatedUserId, playerID);
            return new PVP("Authentication error: User ID mismatch", -1, -1, -1, -1, -1, -1, false, -1, -1, "");
        }
        
        // Update timestamp for active combat sessions
        if (combatDictionary.containsKey(playerID)) {
            combatTimestamps.put(playerID, System.currentTimeMillis());
        }

        logger.debug("Processing PVP request: apiStage={}, playerID={}", apiStage, playerID);

        switch (apiStage) {
            //Match setup stage
            case 0:
                Character tempPlayer = findPlayerDigi(playerStage, playerDigi);
                Character tempOpponent = findOpponentDigi(opponentStage, opponentDigi);

                // Check for existing match
                pvpInstance existingMatch = combatDictionary.get(playerID);
                if (existingMatch != null) {
                    // Check if match is stale (>5 minutes inactive)
                    Long lastActivity = combatTimestamps.get(playerID);
                    boolean isStale = false;
                    if (lastActivity != null) {
                        long timeSinceLastActivity = System.currentTimeMillis() - lastActivity;
                        if (timeSinceLastActivity > COMBAT_TIMEOUT_MS) {
                            isStale = true;
                            logger.debug("Existing match for player {} is stale (inactive for {}ms) - auto-cleaning", 
                                playerID, timeSinceLastActivity);
                            combatDictionary.remove(playerID);
                            combatTimestamps.remove(playerID);
                        }
                    }
                    
                    // If stale, it's been cleaned up - proceed with new match
                    if (isStale) {
                        // Fall through to create new match
                    } else {
                        // Match exists and is not stale - handle action parameter
                        if ("quit".equalsIgnoreCase(action)) {
                            // User explicitly wants to quit existing match
                            logger.debug("Player {} quitting existing match", playerID);
                            combatDictionary.remove(playerID);
                            combatTimestamps.remove(playerID);
                            // Fall through to create new match
                        } else if ("rejoin".equalsIgnoreCase(action)) {
                            // User wants to resume existing match
                            int playerHP = existingMatch.playerCharacter.getCurrentHp();
                            int opponentHP = existingMatch.opponentCharacter.getCurrentHp();
                            int playerMaxHP = existingMatch.playerMaxHp; // Use stored max HP with vitality bonus
                            int opponentMaxHP = existingMatch.opponentCharacter.getBaseHp();
                            logger.debug("Player {} resuming existing match at round {} - Player HP: {}, Opponent HP: {}", 
                                playerID, existingMatch.combatRound, playerHP, opponentHP);
                            combatTimestamps.put(playerID, System.currentTimeMillis()); // Update activity timestamp
                            // Encode character info in winner field: "playerCharaId|playerStage|opponentCharaId|opponentStage"
                            String characterInfo = String.format("%s|%d|%s|%d", 
                                existingMatch.playerCharacter.getCharaId(), 
                                existingMatch.playerCharacter.getStage(),
                                existingMatch.opponentCharacter.getCharaId(), 
                                existingMatch.opponentCharacter.getStage());
                            return new PVP("Match resumed.", existingMatch.combatRound, 
                                existingMatch.combatRound, 
                                playerHP, 
                                opponentHP,
                                playerMaxHP,
                                opponentMaxHP,
                                false, -1, -1, characterInfo);
                        } else {
                            // No action specified - inform client of existing match
                            logger.debug("Player {} has existing match - prompting for action", playerID);
                            // Encode character info in winner field: "playerCharaId|playerStage|opponentCharaId|opponentStage"
                            String characterInfo = String.format("%s|%d|%s|%d", 
                                existingMatch.playerCharacter.getCharaId(), 
                                existingMatch.playerCharacter.getStage(),
                                existingMatch.opponentCharacter.getCharaId(), 
                                existingMatch.opponentCharacter.getStage());
                            return new PVP("Existing match found. Use action=quit to abandon or action=rejoin to resume.", 
                                existingMatch.combatRound, 
                                existingMatch.combatRound, 
                                existingMatch.playerCharacter.getCurrentHp(), 
                                existingMatch.opponentCharacter.getCurrentHp(),
                                existingMatch.playerMaxHp, // Use stored max HP with vitality bonus
                                existingMatch.opponentCharacter.getBaseHp(),
                                false, -1, -1, characterInfo);
                        }
                    }
                }
                
                // No existing match (or it was cleaned up/quitted) - create new match
                // Reset HP to base HP for new match (Character objects are shared from stats arrays,
                // so we need to reset them in case they were modified in a previous match)
                tempPlayer.setCurrentHp(tempPlayer.getBaseHp());
                tempOpponent.setCurrentHp(tempOpponent.getBaseHp());
                
                // Calculate player's max HP with vitality bonus
                int playerBaseHp = tempPlayer.getBaseHp();
                int playerMaxHp = playerBaseHp;
                if (vitalPoints != null && vitalPoints >= 0) {
                    float hpBonusMultiplier = calculateHpBonusMultiplier(playerStage, vitalPoints);
                    playerMaxHp = (int)(playerBaseHp * hpBonusMultiplier);
                    // Set player's current HP to the bonus HP
                    tempPlayer.setCurrentHp(playerMaxHp);
                    logger.debug("Player vitality bonus applied: {} vital points (stage {}) = {:.1f}% bonus, HP: {} -> {}",
                        vitalPoints, playerStage, (hpBonusMultiplier - 1.0f) * 100, playerBaseHp, playerMaxHp);
                }
                
                if (checkExistingCombat(playerID, tempPlayer, tempOpponent, playerMaxHp)) {
                    logger.debug("Match ID: {} - match setup for player ID: {} - player char: {} - opponent char: {}", 
                        msgID, playerID, tempPlayer.getName(), tempOpponent.getName());
                    return new PVP("Match setup.", 0,-1, tempPlayer.getCurrentHp(), tempOpponent.getCurrentHp(), 
                        playerMaxHp, tempOpponent.getBaseHp(), false, -1, -1,"");
                }
                else {
                    // This shouldn't happen, but handle edge case
                    logger.warn("Unexpected: Player {} match creation failed after cleanup", playerID);
                    return new PVP("Error: Could not create match. Please try again.", -1,-1, -1, -1, -1, -1, false, -1, -1, "");
                }

            //Combat processing stage
            case 1:
                pvpInstance combatInstance = combatDictionary.get(playerID);
                if (combatInstance != null) {
                    if (combatInstance.isCombatComplete()) {
                        logger.debug("Match Number: {} Match over. Winner is: {}", msgID, combatInstance.getWinner().getName());
                        return new PVP("Winner reported. Match over.", 2, combatInstance.combatRound, 
                            combatInstance.playerCharacter.getCurrentHp(), combatInstance.opponentCharacter.getCurrentHp(),
                            combatInstance.playerMaxHp, combatInstance.opponentCharacter.getBaseHp(),
                            false, -1, -1, combatInstance.getWinner().getName());
                    }
                    else {
                        combatRoundWrapper tempCombatRound = combatInstance.processCombatRound(critBar);
                        logger.debug("Match Number: {} - Match processing. Player attack hit: {} Player damage: {} Opponent damage: {}", 
                            msgID, tempCombatRound.playerAttackHit, tempCombatRound.playerCombatDamage, tempCombatRound.opponentCombatDamage);
                        return new PVP("Match processing.", 1, combatInstance.combatRound, 
                            combatInstance.playerCharacter.getCurrentHp(), combatInstance.opponentCharacter.getCurrentHp(),
                            combatInstance.playerMaxHp, combatInstance.opponentCharacter.getBaseHp(),
                            tempCombatRound.playerAttackHit, tempCombatRound.playerCombatDamage, tempCombatRound.opponentCombatDamage, "");
                    }
                }
                else {
                    logger.warn("ERROR: Player ID {} not found in current match. Wrong API state sent.", playerID);
                    break;
                }

            //Winner report stage
            case 2:
                pvpInstance finalInstance = combatDictionary.get(playerID);
                if (finalInstance != null) {
                    if (finalInstance.isCombatComplete()) {
                        int finalRoundCount = finalInstance.combatRound;
                        int finalPlayerHP = finalInstance.playerCharacter.getCurrentHp();
                        int finalOpponentHP = finalInstance.opponentCharacter.getCurrentHp();
                        int finalPlayerMaxHP = finalInstance.playerMaxHp; // Use stored max HP with vitality bonus
                        int finalOpponentMaxHP = finalInstance.opponentCharacter.getBaseHp();
                        finalInstance.resetCombat();
                        combatDictionary.remove(playerID);
                        combatTimestamps.remove(playerID);
                        logger.debug("Match Number: {} Match over. Winner is: {}", msgID, finalInstance.getWinner().getName());
                        msgID++;
                        return new PVP("Match cleaned up. Winner reported.", 3, finalRoundCount, finalPlayerHP, finalOpponentHP,
                            finalPlayerMaxHP, finalOpponentMaxHP, false, -1, -1, finalInstance.getWinner().getName());
                    }
                    else {
                        logger.warn("ERROR: combat not completed. Wrong API state sent for player: {}", playerID);
                    }
                }
                else {
                    logger.warn("Player ID {} not found in current match.", playerID);
                    return new PVP("Player ID not found in current match.", -1,-1, -1, -1, -1, -1, false, -1, -1,"");
                }
        }
        return new PVP("Error processing request", -1,-1, -1, -1, -1, -1, false, -1, -1,"");
    }
}