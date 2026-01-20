package com.example.restservice;

import static java.lang.Math.round;

import com.example.vb_battle_server.Character;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
@Validated
public class BattleController {
    private static final Logger logger = LoggerFactory.getLogger(BattleController.class);
    SecureRandom rand = new SecureRandom();
    boolean round1 = false;
    boolean round2 = false;
    boolean round3 = false;
    boolean round4 = false;

    //Calculation used to check if attack hits.
    boolean ProcessAttacks(Character digi1, Character digi2){
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

    //Records if each attack was a hit or a miss.
    void SetRoundLog(int roundName, boolean hitStatus) {
        switch (roundName)
        {
            case 1:
                round1 = hitStatus;
                break;
            case 2:
                round2 = hitStatus;
                break;
            case 3:
                round3 = hitStatus;
                break;
            case 4:
                round4 = hitStatus;
                break;
        }
    }

    //Combat loop - Checks attacks and applies damage until 4 rounds pass or no HP remains on either Digimon.
    Character CombatLoop(Character digi1, Character digi2) {
        if ((digi1.getCurrentHp() & digi2.getCurrentHp()) > 0) {
            for (int i = 0; i < 4; i++){
                if (ProcessAttacks(digi1, digi2)) {
                    SetRoundLog(i, true);
                    digi2.setCurrentHp(digi2.getCurrentHp() - round(digi1.getBaseAp()));
                } else {
                    SetRoundLog(i, false);
                    digi1.setCurrentHp(digi1.getCurrentHp() - round(digi2.getBaseAp()));
                }
            }
        }
        if (digi1.getCurrentHp() > digi2.getCurrentHp()){
            digi1.setCurrentHp(digi1.getBaseHp());
            digi2.setCurrentHp(digi2.getBaseHp());
            return digi1;
        }
        else {
            digi1.setCurrentHp(digi1.getBaseHp());
            digi2.setCurrentHp(digi2.getBaseHp());
            return digi2;
        }
    }

    Character findPlayerDigi(int stage, String name){
        // Validate input to prevent injection attacks
        if (name == null || name.isEmpty() || name.length() > 100) {
            logger.warn("Invalid character name provided: {}", name);
            return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
        }
        
        if (stage == 0) {
            for (int i = 0; i < VBRookieStats.rookieArray.size(); i++) {
                // Fixed: Use equals() instead of matches() to prevent regex injection
                if (name.equals(VBRookieStats.rookieArray.get(i).getName())) {
                    logger.debug("Found player: {}", VBRookieStats.rookieArray.get(i).getName());
                    return VBRookieStats.rookieArray.get(i);
                }
            }
        }
        else if (stage == 1) {
            for (int i = 0; i < VBChampionStats.championArray.size(); i++) {
                if (name.equals(VBChampionStats.championArray.get(i).getName())) {
                    logger.debug("Found player: {}", VBChampionStats.championArray.get(i).getName());
                    return VBChampionStats.championArray.get(i);
                }
            }
        }
        else if (stage == 2) {
            for (int i = 0; i < VBUltimateStats.ultimateArray.size(); i++) {
                if (name.equals(VBUltimateStats.ultimateArray.get(i).getName())) {
                    logger.debug("Found player: {}", VBUltimateStats.ultimateArray.get(i).getName());
                    return VBUltimateStats.ultimateArray.get(i);
                }
            }
        }
        else if (stage == 3) {
            for (int i = 0; i < VBMegaStats.megaArray.size(); i++) {
                if (name.equals(VBMegaStats.megaArray.get(i).getName())) {
                    logger.debug("Found player: {}", VBMegaStats.megaArray.get(i).getName());
                    return VBMegaStats.megaArray.get(i);
                }
            }
        }
        logger.warn("Character not found: stage={}, name={}", stage, name);
        return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
    }
    Character findOpponentDigi(int stage, String name){
        // Validate input to prevent injection attacks
        if (name == null || name.isEmpty() || name.length() > 100) {
            logger.warn("Invalid character name provided: {}", name);
            return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
        }
        
        if (stage == 0) {
            for (int i = 0; i < VBRookieStats.rookieArray.size(); i++) {
                // Fixed: Use equals() instead of matches() to prevent regex injection
                if (name.equals(VBRookieStats.rookieArray.get(i).getName())) {
                    logger.debug("Found opponent: {}", VBRookieStats.rookieArray.get(i).getName());
                    return VBRookieStats.rookieArray.get(i);
                }
            }
        }
        else if (stage == 1) {
            for (int i = 0; i < VBChampionStats.championArray.size(); i++) {
                if (name.equals(VBChampionStats.championArray.get(i).getName())) {
                    logger.debug("Found opponent: {}", VBChampionStats.championArray.get(i).getName());
                    return VBChampionStats.championArray.get(i);
                }
            }
        }
        else if (stage == 2) {
            for (int i = 0; i < VBUltimateStats.ultimateArray.size(); i++) {
                if (name.equals(VBUltimateStats.ultimateArray.get(i).getName())) {
                    logger.debug("Found opponent: {}", VBUltimateStats.ultimateArray.get(i).getName());
                    return VBUltimateStats.ultimateArray.get(i);
                }
            }
        }
        else if (stage == 3) {
            for (int i = 0; i < VBMegaStats.megaArray.size(); i++) {
                if (name.equals(VBMegaStats.megaArray.get(i).getName())) {
                    logger.debug("Found opponent: {}", VBMegaStats.megaArray.get(i).getName());
                    return VBMegaStats.megaArray.get(i);
                }
            }
        }

        logger.warn("Character not found: stage={}, name={}", stage, name);
        return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
    }

    //Uses stage parameter from incoming Get request to lookup correct Digimon for combat
    @GetMapping("api/battle")
    public Combat combat(
            @RequestParam(value = "playerDigi") @NotBlank String playerDigi,
            @RequestParam(value = "playerStage") @Min(0) @Max(3) int playerStage,
            @RequestParam(value = "opponentDigi") @NotBlank String opponentDigi,
            @RequestParam(value = "opponentStage") @Min(0) @Max(3) int opponentStage) {
        
        logger.debug("Battle request: playerDigi={}, playerStage={}, opponentDigi={}, opponentStage={}", 
            playerDigi, playerStage, opponentDigi, opponentStage);
        
        Character tempPlayer = findPlayerDigi(playerStage, playerDigi);
        Character tempOpponent = findOpponentDigi(opponentStage, opponentDigi);
        Character winner = CombatLoop(tempPlayer, tempOpponent);
        
        logger.info("Battle completed. Winner: {}", winner.getName());
        
        switch(winner.getStage()){
            case 0:
                VBRookieStats.rookieArray.add(winner);
                break;
            case 1:
                VBChampionStats.championArray.add(winner);
                break;
            case 2:
                VBUltimateStats.ultimateArray.add(winner);
                break;
            case 3:
                VBMegaStats.megaArray.add(winner);
                break;
        }
        return new Combat(winner.getName(), round1, round2, round3, round4);
    }
}