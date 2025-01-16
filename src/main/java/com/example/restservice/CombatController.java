package com.example.restservice;

import static java.lang.Math.round;
import java.util.concurrent.atomic.AtomicLong;

import com.example.vb_battle_server.Character;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CombatController {

    private static final String template = "Winner is %s! Round 1: %s. Round 2: %s. Round 3: %s. Round 4: %s.";
    private final AtomicLong counter = new AtomicLong();
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
    private final Character digimon2 = new Character("ShellNumemon",1,2,3000, 3000,4800,1150);

    String round1 = "";
    String round2 = "";
    String round3 = "";
    String round4 = "";

    boolean CombatRound(Character digi1, Character digi2){
        float hitrate = (digi1.getBaseBp() / (digi1.getBaseBp() + digi2.getBaseBp()) * 100);
        int rand_int = rand.nextInt(99);
        if (rand_int < hitrate) {
            return true;
        }
        else {
            return false;
        }
    }

    void SetRoundLog(String roundName, boolean hitStatus) {
        switch (roundName)
        {
            case "round1":
                round1 = String.valueOf(hitStatus);
            case "round2":
                round2 = String.valueOf(hitStatus);
            case "round3":
                round3 = String.valueOf(hitStatus);
            case "round4":
                round4 = String.valueOf(hitStatus);
        }
    }

    Character CombatCheck(Character digi1, Character digi2) {
        String roundName = "round";

        if ((digi1.getCurrentHp() & digi2.getCurrentHp()) > 0) {
            for (int i = 0; i < 4; i++){
                roundName = "round" + i;
                if (CombatRound(digi1, digi2)) {
                    SetRoundLog(roundName, true);
                    digi2.setCurrentHp(digi2.getCurrentHp() - round(digi1.getBaseAp()));
                } else {
                    SetRoundLog(roundName, false);
                    digi1.setCurrentHp(digi1.getCurrentHp() - round(digi2.getBaseAp()));
                }
            }
        }
        if (digi1.getCurrentHp() > digi2.getCurrentHp()){
            digi1.setCurrentHp(3700);
            digi2.setCurrentHp(3000);
            return digi1;
        }
        else {
            digi1.setCurrentHp(3700);
            digi2.setCurrentHp(3000);
            return digi2;
        }
    }

    @GetMapping("/combat")
    public Combat combat() {
        Character winner = CombatCheck(digimon1, digimon2);
        return new Combat(counter.incrementAndGet(), String.format(template, winner.getName(), round1, round2, round3, round4));
    }
}