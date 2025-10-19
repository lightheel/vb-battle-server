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

    ArrayList<Character> OpponentsByStage(int stage) {
        switch (stage) {
            case 0:
                return VBRookieStats.rookieArray;
            case 1:
                return VBChampionStats.championArray;
            case 2:
                return VBUltimateStats.ultimateArray;
            case 3:
                return VBMegaStats.megaArray;
            default:
                return new ArrayList<>();
        }
    }
    
    //Uses stage parameter from incoming Get request to lookup correct Digimon for combat
    @GetMapping("api/opponents")
    public Opponents opponents(@RequestParam(value = "stage") String stage) {
        ArrayList<Character> opponentsArray = OpponentsByStage(CheckStage(stage));
        return new Opponents(opponentsArray);
    }
}