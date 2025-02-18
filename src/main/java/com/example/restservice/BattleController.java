package com.example.restservice;

import static java.lang.Math.round;

import com.example.vb_battle_server.Character;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BattleController {
    Random rand = new Random();
    boolean round1 = false;
    boolean round2 = false;
    boolean round3 = false;
    boolean round4 = false;

    //Calculation used to check if attack hits.
    boolean ProcessAttacks(Character digi1, Character digi2){
        float hitrate = (digi1.getBaseBp() / (digi1.getBaseBp() + digi2.getBaseBp()) * 100);
        int rand_int = rand.nextInt(99);
        return rand_int < hitrate;
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

    Character findPlayerDigi(String name){
        for (int i = 0; i < RookieStats.rookieArray.size(); i++) {
            if (RookieStats.rookieArray.get(i).getName().matches(name)) {
                System.out.println("found player: " + RookieStats.rookieArray.get(i).getName());
                return RookieStats.rookieArray.get(i);
            }
        }
        //System.out.println("REALLY found no opponent");
        return new Character("", 0, 0, 0, 0, 0 ,0);
    }
    Character findOpponentDigi(String name){
        for (int i = 0; i < RookieStats.rookieArray.size(); i++) {
            if (RookieStats.rookieArray.get(i).getName().matches(name)) {
                System.out.println("found opponent: " + RookieStats.rookieArray.get(i).getName());
                return RookieStats.rookieArray.get(i);
            }
        }
        //System.out.println("REALLY found no opponent");
        return new Character("", 0, 0, 0, 0, 0 ,0);
    }


    //Uses stage parameter from incoming Get request to lookup correct Digimon for combat
    @GetMapping("api/battle")
    public Combat combat(@RequestParam(value = "playerDigi") String playerDigi, @RequestParam(value = "opponentDigi") String opponentDigi) {
        //System.out.println("sent player digi: " + playerDigi);
        Character tempPlayer = findPlayerDigi(playerDigi);
        //System.out.println("sent opponent digi: " + opponentDigi);
        Character tempOpponent = findOpponentDigi(opponentDigi);
        Character winner = CombatLoop(tempPlayer, tempOpponent);
        return new Combat(winner.getName(), round1, round2, round3, round4);
    }
}