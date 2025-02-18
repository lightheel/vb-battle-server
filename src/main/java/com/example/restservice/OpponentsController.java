package com.example.restservice;

import com.example.vb_battle_server.Character;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpponentsController {

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
    ArrayList<Character> characterArray = new ArrayList<>();
    ArrayList<Character> OpponentsByStage(int stage){
        characterArray = new ArrayList<>();

        switch (stage){
            case 0:
                characterArray.add(RookieStats.agumon);
                characterArray.add(RookieStats.pulsemon);
                characterArray.add(RookieStats.dorumon);
                return characterArray;
            case 1:
                characterArray.add(ChampionStats.greymon);
                characterArray.add(ChampionStats.betelGammamon);
                characterArray.add(ChampionStats.dorugamon);
                return characterArray;
            case 2:
                characterArray.add(UltimateStats.doruguremon);
                characterArray.add(UltimateStats.metalGreymon);
                characterArray.add(UltimateStats.groundramon);
                return characterArray;
            case 3:
                characterArray.add(MegaStats.machinedramon);
                characterArray.add(MegaStats.dorugoramon);
                characterArray.add(MegaStats.jesmon);
                return characterArray;
            default:
                return characterArray;

        }
    }

    //Uses stage parameter from incoming Get request to lookup correct Digimon for combat
    @GetMapping("api/opponents")
    public Opponents opponents(@RequestParam(value = "stage") String stage) {
        ArrayList<Character> opponentsArray = OpponentsByStage(CheckStage(stage));
        return new Opponents(opponentsArray);
    }
}
