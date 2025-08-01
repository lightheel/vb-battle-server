package com.example.restservice;

import com.example.vb_battle_server.Character;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;


class combatRoundWrapper{
    int playerCombatDamage;
    int opponentCombatDamage;
    boolean playerAttackHit;
}
@RestController
public class PVPController {

    int msgID = 0;
    Random rand = new Random();

    //Calculation used to check if attack hits.
    boolean checkAttackHit(Character digi1, Character digi2) {
        float hitrate = (digi1.getBaseBp() / (digi1.getBaseBp() + digi2.getBaseBp()) * 100);
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
        if (stage == 0) {
            for (int i = 0; i < VBRookieStats.rookieArray.size(); i++) {
                if (VBRookieStats.rookieArray.get(i).getName().matches(name)) {
                    return VBRookieStats.rookieArray.get(i);
                }
            }
        }
        else if (stage == 1) {
            for (int i = 0; i < VBChampionStats.championArray.size(); i++) {
                if (VBChampionStats.championArray.get(i).getName().matches(name)) {
                    return VBChampionStats.championArray.get(i);
                }
            }
        }
        else if (stage == 2) {
            for (int i = 0; i < VBUltimateStats.ultimateArray.size(); i++) {
                if (VBUltimateStats.ultimateArray.get(i).getName().matches(name)) {
                    return VBUltimateStats.ultimateArray.get(i);
                }
            }
        }
        else if (stage == 3) {
            for (int i = 0; i < VBMegaStats.megaArray.size(); i++) {
                if (VBMegaStats.megaArray.get(i).getName().matches(name)) {
                    return VBMegaStats.megaArray.get(i);
                }
            }
        }

        return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
    }
    Character findOpponentDigi(int stage, String name){
        if (stage == 0) {
            for (int i = 0; i < VBRookieStats.rookieArray.size(); i++) {
                if (VBRookieStats.rookieArray.get(i).getName().matches(name)) {
                    return VBRookieStats.rookieArray.get(i);
                }
            }
        }
        else if (stage == 1) {
            for (int i = 0; i < VBChampionStats.championArray.size(); i++) {
                if (VBChampionStats.championArray.get(i).getName().matches(name)) {
                    return VBChampionStats.championArray.get(i);
                }
            }
        }
        else if (stage == 2) {
            for (int i = 0; i < VBUltimateStats.ultimateArray.size(); i++) {
                if (VBUltimateStats.ultimateArray.get(i).getName().matches(name)) {
                    return VBUltimateStats.ultimateArray.get(i);
                }
            }
        }
        else if (stage == 3) {
            for (int i = 0; i < VBMegaStats.megaArray.size(); i++) {
                if (VBMegaStats.megaArray.get(i).getName().matches(name)) {
                    return VBMegaStats.megaArray.get(i);
                }
            }
        }

        return new Character("", "", "", 0, 0, 0, 0, 0.0f, 0.0f);
    }

    Dictionary<String, pvpInstance> combatDictionary;

    boolean checkExistingCombat(String playerID, Character playerCharacter, Character opponentCharacter) {
        //Sets up dictionary after server has first started.
        if (combatDictionary == null) {
            combatDictionary = new Hashtable<>();
            combatDictionary.put(playerID, new pvpInstance(playerID, playerCharacter, opponentCharacter));
            return true;
        }
        else {
            //Adds combat if player ID isn't already in a match.
            if (combatDictionary.get(playerID) == null) {
                combatDictionary.put(playerID, new pvpInstance(playerID, playerCharacter, opponentCharacter));
                return true;
            }
            //Player ID already in active match
            else {
                return false;
            }
        }
    }
    @GetMapping("api/pvp")
    public PVP pvp(@RequestParam(value = "apiStage") int apiStage, @RequestParam(value = "playerID") String playerID, @RequestParam(value = "playerDigi") String playerDigi, @RequestParam(value = "playerStage") int playerStage, @RequestParam(value = "critBar") float critBar, @RequestParam(value = "opponentDigi") String opponentDigi, @RequestParam(value = "opponentStage") int opponentStage) {

        System.out.println("api stage: " + apiStage);

        switch (apiStage) {
            //Match setup stage
            case 0:
                Character tempPlayer = findPlayerDigi(playerStage, playerDigi);
                Character tempOpponent = findOpponentDigi(opponentStage, opponentDigi);

                if (checkExistingCombat(playerID, tempPlayer, tempOpponent)) {
                    return new PVP("Match setup.", 0,-1, tempPlayer.getCurrentHp(), tempOpponent.getCurrentHp(), false, -1, -1,"");
                }
                else {
                    return new PVP("Player already in active match. Nothing happens.", 0,-1, tempPlayer.getCurrentHp(), tempOpponent.getCurrentHp(), false, -1, -1, "");
                }

            //Combat processing stage
            case 1:
                if (combatDictionary != null) {
                    if (combatDictionary.get(playerID) != null) {
                        if (combatDictionary.get(playerID).isCombatComplete()) {
                            System.out.println("Message ID: " + msgID++ + " Match over. Winner is: " + combatDictionary.get(playerID).getWinner().getName());
                            return new PVP("Winner reported. Match over.", 2, combatDictionary.get(playerID).combatRound, combatDictionary.get(playerID).playerCharacter.getCurrentHp(), combatDictionary.get(playerID).opponentCharacter.getCurrentHp(), false, -1, -1, combatDictionary.get(playerID).getWinner().getName());
                        }
                        else {
                            combatRoundWrapper tempCombatRound = combatDictionary.get(playerID).processCombatRound(critBar);
                            return new PVP("Match processing.", 1, combatDictionary.get(playerID).combatRound, combatDictionary.get(playerID).playerCharacter.getCurrentHp(), combatDictionary.get(playerID).opponentCharacter.getCurrentHp(), tempCombatRound.playerAttackHit, tempCombatRound.playerCombatDamage, tempCombatRound.opponentCombatDamage, "");
                        }
                    }
                    else {
                        System.out.println("ERROR: Player ID not found in current match. Wrong API state sent.");
                        break;
                    }
                }
                else {
                    System.out.println("ERROR: Combat dictionary is null.");
                    break;
                }

            //Winner report stage
            case 2:
                if (combatDictionary != null) {
                    if (combatDictionary.get(playerID) != null) {
                        if (combatDictionary.get(playerID).isCombatComplete()) {
                            pvpInstance tempInstance = combatDictionary.get(playerID);
                            int finalRoundCount = tempInstance.combatRound;
                            int finalPlayerHP = tempInstance.playerCharacter.getCurrentHp();
                            int finalOpponentHP = tempInstance.opponentCharacter.getCurrentHp();
                            combatDictionary.get(playerID).resetCombat();
                            combatDictionary.remove(playerID);
                            return new PVP("Match cleaned up. Winner reported.", 3, finalRoundCount, finalPlayerHP, finalOpponentHP, false, -1, -1, tempInstance.getWinner().getName());
                        }
                        else {
                            System.out.println("ERROR: combat not completed. Wrong API state sent.");
                        }
                    }
                    else {
                        return new PVP("Player ID not found in current match.", -1,-1, -1, -1, false, -1, -1,"");
                    }
                }
        }
        return new PVP("Error processing request", -1,-1, -1, -1, false, -1, -1,"");
    }
}