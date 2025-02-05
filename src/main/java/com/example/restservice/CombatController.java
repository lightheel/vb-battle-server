package com.example.restservice;

import static java.lang.Math.round;

import com.example.vb_battle_server.Character;

import java.util.Random;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombatController {
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

    //Maps stage string to integer
    int CheckStage(String stage){
        return switch (stage) {
            case "rookie" -> 0;
            case "champion" -> 1;
            case "ultimate" -> 2;
            case "mega" -> 3;
            default -> -1;
        };
    }

    //Returns array of specific Digimon for combat for each stage
    ArrayList<Character> CombatStartByStage(int stage){
        ArrayList<Character> characterArray = new ArrayList<>();

        switch (stage){
            case 0:
                characterArray.add(RookieStats.agumon);
                characterArray.add(RookieStats.pulsemon);
                return characterArray;
            case 1:
                characterArray.add(ChampionStats.greymon);
                characterArray.add(ChampionStats.betelGammamon);
                return characterArray;
            case 2:
                characterArray.add(UltimateStats.doruguremon);
                characterArray.add(UltimateStats.metalGreymon);
                return characterArray;
            case 3:
                characterArray.add(MegaStats.machinedramon);
                characterArray.add(MegaStats.dorugoramon);
                return characterArray;
            default:
                return characterArray;

        }
    }

    //Uses stage parameter from incoming Get request to lookup correct Digimon for combat
    @GetMapping("api/combat")
    public Combat combat(@RequestParam(value = "stage") String stage) {
        ArrayList<Character> characterCombatArray = CombatStartByStage(CheckStage(stage));
        Character winner = CombatLoop(characterCombatArray.get(0), characterCombatArray.get(1));
        return new Combat(winner.getName(), round1, round2, round3, round4);
    }
}