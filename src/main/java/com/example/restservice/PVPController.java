package com.example.restservice;

import static java.lang.Math.round;

import com.example.vb_battle_server.Character;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@RestController
public class PVPController {
    Random rand = new Random();
    boolean round1 = false;
    boolean round2 = false;
    boolean round3 = false;
    boolean round4 = false;

    //Calculation used to check if attack hits.
    boolean checkAttackHit(Character digi1, Character digi2){
        float hitrate = (digi1.getBaseBp() / (digi1.getBaseBp() + digi2.getBaseBp()) * 100);
        int rand_int = rand.nextInt(99);
        return rand_int < hitrate;
    }

    class pvpInstance {

        Character winner;
        boolean combatComplete;
        int combatRound;
        int playerID;
        Character playerCharacter;
        Character getPlayerCharacter() {
            return playerCharacter;
        }
        Character opponentCharacter;
        Character getOpponentCharacter() {
            return opponentCharacter;
        }

        public pvpInstance(int inputPlayerID, Character inputPlayerCharacter, Character inputOpponentCharacter) {
            playerID = inputPlayerID;
            playerCharacter = inputPlayerCharacter;
            opponentCharacter = inputOpponentCharacter;
        }
        boolean findCombat(int ID){
            return playerID == ID;
        }

        boolean isCombatComplete() {
            if (playerCharacter.getCurrentHp() <= 0) {
                System.out.println("player HP under 0");
                winner = opponentCharacter;
                return true;
            }
            else if (opponentCharacter.getCurrentHp() <= 0) {
                System.out.println("opponent HP under 0");
                winner = playerCharacter;
                return true;
            }
            else {
                //System.out.println("combat still going");
                return false;
            }
        }

        void setWinner() {

        }
        Character getWinner() {
            return winner;
        }

        void processRound(float critBar){
            //System.out.println("crit bar: " + critBar);
            int attackDamage = 0;
            int randTotal = rand.nextInt(15) + rand.nextInt(15) + rand.nextInt(15) + rand.nextInt(15) + rand.nextInt(15);

            if (checkAttackHit(playerCharacter, opponentCharacter)) {
                if (critBar == 100.0f) {
                    attackDamage = ((((int)playerCharacter.getBaseAp() / 4) / 10 ) * randTotal) + randTotal;
                }
                else {
                    attackDamage = ((((int)playerCharacter.getBaseAp() / 4) / 10 ) * randTotal);
                }
                //deal damage to player
                playerCharacter.setCurrentHp(playerCharacter.getCurrentHp() - attackDamage);
                System.out.println("player HP: " + playerCharacter.getCurrentHp());
            }
            else {
                if (rand.nextInt(99) >= 90) {
                    attackDamage = ((((int)playerCharacter.getBaseAp() / 4) / 10 ) * randTotal) + randTotal;
                }
                else {
                    attackDamage = ((((int)playerCharacter.getBaseAp() / 4) / 10 ) * randTotal);
                }
                //deal damage to enemy
                opponentCharacter.setCurrentHp(opponentCharacter.getCurrentHp() - attackDamage);
                System.out.println("opponent HP: " + opponentCharacter.getCurrentHp());
            }
        }
    }

    ArrayList<pvpInstance> combatArray;

    int checkArray(int playerID, Character playerCharacter, Character opponentCharacter) {
        if (combatArray == null) {
            combatArray = new ArrayList<pvpInstance>();
            combatArray.add(new pvpInstance(playerID, playerCharacter, opponentCharacter));
            //System.out.println("array is empty. adding ID: " + playerID);
            return -1;
        }
        else {
            for (int i = 0; i < combatArray.size(); i++) {
                //System.out.println("Player ID: " + playerID);
                //System.out.println("array ID: " + combatArray.get(i).playerID);
                if (playerID == combatArray.get(i).playerID) {
                    //System.out.println("player already in active combat: " + combatArray.get(i).getPlayerCharacter() + combatArray.get(i).getOpponentCharacter());
                    return i;
                }
                else {
                    //System.out.println("array not empty, but player not found: ");
                    combatArray.add(new pvpInstance(playerID, playerCharacter, opponentCharacter));
                    return i;
                }
            }
        }
        return -2;
    }

    //Records if each attack was a hit or a miss.
    void SetRoundLog(int roundName, boolean hitStatus) {
        switch (roundName)
        {
            case 1:
                round1 = hitStatus;
            case 2:
                round2 = hitStatus;
            case 3:
                round3 = hitStatus;
            case 4:
                round4 = hitStatus;
        }
    }

    //Combat loop - Checks attacks and applies damage until 4 rounds pass or no HP remains on either Digimon.
    Character CombatLoop(Character digi1, Character digi2) {
        if ((digi1.getCurrentHp() & digi2.getCurrentHp()) > 0) {
            for (int i = 0; i < 4; i++){
                if (checkAttackHit(digi1, digi2)) {
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
        if (stage == 0) {
            for (int i = 0; i < RookieStats.rookieArray.size(); i++) {
                if (RookieStats.rookieArray.get(i).getName().matches(name)) {
                    //System.out.println("found player: " + RookieStats.rookieArray.get(i).getName());
                    return RookieStats.rookieArray.get(i);
                }
            }
        }
        else if (stage == 1) {
            for (int i = 0; i < ChampionStats.championArray.size(); i++) {
                if (ChampionStats.championArray.get(i).getName().matches(name)) {
                    //System.out.println("found player: " + ChampionStats.championArray.get(i).getName());
                    return ChampionStats.championArray.get(i);
                }
            }
        }
        else if (stage == 2) {
            for (int i = 0; i < UltimateStats.ultimateArray.size(); i++) {
                if (UltimateStats.ultimateArray.get(i).getName().matches(name)) {
                    //System.out.println("found player: " + UltimateStats.ultimateArray.get(i).getName());
                    return UltimateStats.ultimateArray.get(i);
                }
            }
        }
        else if (stage == 3) {
            for (int i = 0; i < MegaStats.megaArray.size(); i++) {
                if (MegaStats.megaArray.get(i).getName().matches(name)) {
                    //System.out.println("found player: " + MegaStats.megaArray.get(i).getName());
                    return MegaStats.megaArray.get(i);
                }
            }
        }
        //System.out.println("found no player char");
        return new Character("", 0, 0, 0, 0, 0 ,0);
    }
    Character findOpponentDigi(int stage, String name){
        if (stage == 0) {
            for (int i = 0; i < RookieStats.rookieArray.size(); i++) {
                if (RookieStats.rookieArray.get(i).getName().matches(name)) {
                    //System.out.println("found opponent: " + RookieStats.rookieArray.get(i).getName());
                    return RookieStats.rookieArray.get(i);
                }
            }
        }
        else if (stage == 1) {
            for (int i = 0; i < ChampionStats.championArray.size(); i++) {
                if (ChampionStats.championArray.get(i).getName().matches(name)) {
                    //System.out.println("found opponent: " + ChampionStats.championArray.get(i).getName());
                    return ChampionStats.championArray.get(i);
                }
            }
        }
        else if (stage == 2) {
            for (int i = 0; i < UltimateStats.ultimateArray.size(); i++) {
                if (UltimateStats.ultimateArray.get(i).getName().matches(name)) {
                    //System.out.println("found opponent: " + UltimateStats.ultimateArray.get(i).getName());
                    return UltimateStats.ultimateArray.get(i);
                }
            }
        }
        else if (stage == 3) {
            for (int i = 0; i < MegaStats.megaArray.size(); i++) {
                if (MegaStats.megaArray.get(i).getName().matches(name)) {
                    //System.out.println("found opponent: " + MegaStats.megaArray.get(i).getName());
                    return MegaStats.megaArray.get(i);
                }
            }
        }

        //System.out.println("found no opponent char");
        return new Character("", 0, 0, 0, 0, 0 ,0);
    }

    //Uses stage parameter from incoming Get request to lookup correct Digimon for combat
    @GetMapping("api/pvp")
    public PVP pvp(@RequestParam(value = "playerID") int playerID, @RequestParam(value = "playerDigi") String playerDigi, @RequestParam(value = "playerStage") int playerStage, @RequestParam(value = "critBar") float critBar, @RequestParam(value = "opponentDigi") String opponentDigi, @RequestParam(value = "opponentStage") int opponentStage) {
        //System.out.println("sent player digi: " + playerDigi);
        //System.out.println("sent opponent digi: " + opponentDigi);
        Character winner = null;
        Character tempPlayer = findPlayerDigi(playerStage, playerDigi);
        Character tempOpponent = findOpponentDigi(opponentStage, opponentDigi);
        //System.out.println("sent player ID " + playerID);
        int arrayIndex = checkArray(playerID, tempPlayer, tempOpponent);
        //System.out.println("array index: " + arrayIndex);
        if (arrayIndex >= 0) {
            //System.out.println("combat array index is set");
            if (combatArray.get(arrayIndex) != null) {
                if (combatArray.get(arrayIndex).isCombatComplete()) {
                    //System.out.println("combat not completed");
                    System.out.println("winner is: " + combatArray.get(arrayIndex).winner.getName());
                }
                else {
                    combatArray.get(arrayIndex).processRound(critBar);
                }
            }
            else {
                System.out.println("big nulls");
            }

        }
        else if (arrayIndex == -2) {
            System.out.println("couldn't locate or process combat array");
        }

        if (winner != null) {
            switch(winner.getStage()){
                case 0:
                    OpponentsController.addRookie(winner);
                case 1:
                    OpponentsController.addChampion(winner);
                case 2:
                    OpponentsController.addUltimate(winner);
                case 3:
                    OpponentsController.addMega(winner);
            }
            return new PVP(winner.getName(), round1, round2, round3, round4);
        }
        else {
            return new PVP("", round1, round2, round3, round4);
        }
    }
}