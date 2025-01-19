package com.example.restservice;

import static java.lang.Math.round;

import com.example.vb_battle_server.Character;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombatController {
    Random rand = new Random();

    //Stages
    // 0 Rookie
    // 1 Champion
    // 2 Ultimate
    // 3 Mega

    //Attributes
    // 0 None
    // 1 Virus
    // 2 Data
    // 3 Vaccine
    // 4 Free
    private final Character digimon1 = new Character("Gulus Gammamon",1,2,3700, 3700, 5300,1250);
    private final Character digimon2 = new Character("Shell Numemon",1,2,3000, 3000,4800,1150);

    boolean round1 = false;
    boolean round2 = false;
    boolean round3 = false;
    boolean round4 = false;
    boolean CombatRound(Character digi1, Character digi2){
        float hitrate = (digi1.getBaseBp() / (digi1.getBaseBp() + digi2.getBaseBp()) * 100);
        int rand_int = rand.nextInt(99);
        return rand_int < hitrate;
    }

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

    Character CombatCheck(Character digi1, Character digi2) {
        if ((digi1.getCurrentHp() & digi2.getCurrentHp()) > 0) {
            for (int i = 0; i < 4; i++){
                if (CombatRound(digi1, digi2)) {
                    SetRoundLog(i, true);
                    digi2.setCurrentHp(digi2.getCurrentHp() - round(digi1.getBaseAp()));
                } else {
                    SetRoundLog(i, false);
                    digi1.setCurrentHp(digi1.getCurrentHp() - round(digi2.getBaseAp()));
                }
            }
        }
        if (digi1.getCurrentHp() > digi2.getCurrentHp()){
            digi1.setCurrentHp(digimon1.getBaseHp());
            digi2.setCurrentHp(digimon2.getBaseHp());
            return digi1;
        }
        else {
            digi1.setCurrentHp(digimon1.getBaseHp());
            digi2.setCurrentHp(digimon2.getBaseHp());
            return digi2;
        }
    }

    @GetMapping("/combat")
    public Combat combat() {
        Character winner = CombatCheck(digimon1, digimon2);
        return new Combat(winner.getName(), round1, round2, round3, round4);
    }
}