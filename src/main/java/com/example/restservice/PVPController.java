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
            return rand.nextInt(100) < 50;
        }
        float hitrate = (digi1.getBaseBp() / totalBp * 100);
        int rand_int = rand.nextInt(99);
        return rand_int < hitrate;
    }

    //Combat instance class
    class pvpInstance {
        Character winner;
        int combatRound = 0;
        String playerID;
        Character playerCharacter;
        Character opponentCharacter;

        public pvpInstance(String inputPlayerID, Character inputPlayerCharacter, Character inputOpponentCharacter) {
            playerID = inputPlayerID;
            playerCharacter = inputPlayerCharacter;
            opponentCharacter = inputOpponentCharacter;
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
            int randTotal = rand.nextInt(15) + rand.nextInt(15) + rand.nextInt(15) + rand.nextInt(15) + rand.nextInt(15);

            if (checkAttackHit(playerCharacter, opponentCharacter)) {
                if (critBar == 100.0f) {
                    attackDamage = ((((int)playerCharacter.getBaseAp() / 4) / 10 ) * randTotal) + randTotal;
                    tempRoundWrapper.opponentCombatDamage = attackDamage;
                }
                else {
                    attackDamage = ((((int)playerCharacter.getBaseAp() / 4) / 10 ) * randTotal);
                    tempRoundWrapper.opponentCombatDamage = attackDamage;
                }
                //deal damage to player
                playerCharacter.setCurrentHp(playerCharacter.getCurrentHp() - attackDamage);
                //System.out.println("player HP: " + playerCharacter.getCurrentHp());
                tempRoundWrapper.playerAttackHit = false;
                return tempRoundWrapper;
            }
            else {
                if (rand.nextInt(99) >= 90) {
                    attackDamage = ((((int)playerCharacter.getBaseAp() / 4) / 10 ) * randTotal) + randTotal;
                    tempRoundWrapper.playerCombatDamage = attackDamage;
                }
                else {
                    attackDamage = ((((int)playerCharacter.getBaseAp() / 4) / 10 ) * randTotal);
                    tempRoundWrapper.playerCombatDamage = attackDamage;
                }
                //deal damage to enemy
                opponentCharacter.setCurrentHp(opponentCharacter.getCurrentHp() - attackDamage);
                //System.out.println("opponent HP: " + opponentCharacter.getCurrentHp());

                tempRoundWrapper.playerAttackHit = true;
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
        logger.debug("Searching for player char: {}", name);
        
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
        logger.debug("Searching for opponent char: {}", name);
        
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

    boolean checkExistingCombat(String playerID, Character playerCharacter, Character opponentCharacter) {
        // Validate playerID
        if (playerID == null || playerID.isEmpty() || playerID.length() > 200) {
            logger.warn("Invalid playerID provided: {}", playerID);
            return false;
        }
        
        // Adds combat if player ID isn't already in a match.
        pvpInstance existing = combatDictionary.putIfAbsent(playerID, 
            new pvpInstance(playerID, playerCharacter, opponentCharacter));
        
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
    @GetMapping("api/pvp")
    public PVP pvp(
            @RequestParam(value = "apiStage") @Min(0) @Max(2) int apiStage,
            @RequestParam(value = "playerID") @NotBlank String playerID,
            @RequestParam(value = "playerDigi") @NotBlank String playerDigi,
            @RequestParam(value = "playerStage") @Min(0) @Max(3) int playerStage,
            @RequestParam(value = "critBar") @Min(0) @Max(100) float critBar,
            @RequestParam(value = "opponentDigi") @NotBlank String opponentDigi,
            @RequestParam(value = "opponentStage") @Min(0) @Max(3) int opponentStage) {

        // Optional: Verify authenticated user matches playerID if token was provided
        // This allows the endpoint to work with just playerID (original behavior)
        // but still validates if a token is present
        String authenticatedUserId = getAuthenticatedUserId();
        if (authenticatedUserId != null && !authenticatedUserId.equals(playerID)) {
            logger.warn("User ID mismatch: authenticated={}, requested={}", authenticatedUserId, playerID);
            return new PVP("Authentication error: User ID mismatch", -1, -1, -1, -1, false, -1, -1, "");
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

                if (checkExistingCombat(playerID, tempPlayer, tempOpponent)) {
                    logger.debug("Match ID: {} - match setup for player ID: {} - player char: {} - opponent char: {}", 
                        msgID, playerID, tempPlayer.getName(), tempOpponent.getName());
                    return new PVP("Match setup.", 0,-1, tempPlayer.getCurrentHp(), tempOpponent.getCurrentHp(), false, -1, -1,"");
                }
                else {
                    logger.debug("Player {} already in active match", playerID);
                    return new PVP("Player already in active match. Nothing happens.", 0,-1, tempPlayer.getCurrentHp(), tempOpponent.getCurrentHp(), false, -1, -1, "");
                }

            //Combat processing stage
            case 1:
                pvpInstance combatInstance = combatDictionary.get(playerID);
                if (combatInstance != null) {
                    if (combatInstance.isCombatComplete()) {
                        logger.debug("Match Number: {} Match over. Winner is: {}", msgID, combatInstance.getWinner().getName());
                        return new PVP("Winner reported. Match over.", 2, combatInstance.combatRound, 
                            combatInstance.playerCharacter.getCurrentHp(), combatInstance.opponentCharacter.getCurrentHp(), 
                            false, -1, -1, combatInstance.getWinner().getName());
                    }
                    else {
                        combatRoundWrapper tempCombatRound = combatInstance.processCombatRound(critBar);
                        logger.debug("Match Number: {} - Match processing. Player attack hit: {} Player damage: {} Opponent damage: {}", 
                            msgID, tempCombatRound.playerAttackHit, tempCombatRound.playerCombatDamage, tempCombatRound.opponentCombatDamage);
                        return new PVP("Match processing.", 1, combatInstance.combatRound, 
                            combatInstance.playerCharacter.getCurrentHp(), combatInstance.opponentCharacter.getCurrentHp(), 
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
                        finalInstance.resetCombat();
                        combatDictionary.remove(playerID);
                        combatTimestamps.remove(playerID);
                        logger.debug("Match Number: {} Match over. Winner is: {}", msgID, finalInstance.getWinner().getName());
                        msgID++;
                        return new PVP("Match cleaned up. Winner reported.", 3, finalRoundCount, finalPlayerHP, finalOpponentHP, false, -1, -1, finalInstance.getWinner().getName());
                    }
                    else {
                        logger.warn("ERROR: combat not completed. Wrong API state sent for player: {}", playerID);
                    }
                }
                else {
                    logger.warn("Player ID {} not found in current match.", playerID);
                    return new PVP("Player ID not found in current match.", -1,-1, -1, -1, false, -1, -1,"");
                }
        }
        return new PVP("Error processing request", -1,-1, -1, -1, false, -1, -1,"");
    }
}