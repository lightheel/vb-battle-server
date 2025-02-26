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

    boolean initialOpponentSetup = false;
    static public ArrayList<Character> rookieArray = new ArrayList<>();
    static public void addRookie(Character character) {
        rookieArray.add(character);
    }
    static public ArrayList<Character> championArray = new ArrayList<>();
    static public void addChampion(Character character) {
        championArray.add(character);
    }
    static public ArrayList<Character> ultimateArray = new ArrayList<>();
    static public void addUltimate(Character character) {
        ultimateArray.add(character);
    }
    static public ArrayList<Character> megaArray = new ArrayList<>();
    static public void addMega(Character character) {
        megaArray.add(character);
    }
    ArrayList<Character> OpponentsByStage(int stage) {
        //characterArray = new ArrayList<>();

        if (initialOpponentSetup) {
            switch (stage) {
                case 0:
                    return rookieArray;
                case 1:
                    return championArray;
                case 2:
                    return ultimateArray;
                case 3:
                    return megaArray;
                default:
                    return new ArrayList<>();

            }
        }
        else {
            initialOpponentSetup = true;
            switch (stage) {
                case 0:
                    rookieArray.add(RookieStats.agumon);
                    rookieArray.add(RookieStats.pulsemon);
                    rookieArray.add(RookieStats.dorumon);
                    return rookieArray;
                case 1:
                    championArray.add(ChampionStats.greymon);
                    championArray.add(ChampionStats.betelGammamon);
                    championArray.add(ChampionStats.dorugamon);
                    return championArray;
                case 2:
                    ultimateArray.add(UltimateStats.doruguremon);
                    ultimateArray.add(UltimateStats.metalGreymon);
                    ultimateArray.add(UltimateStats.groundramon);
                    return ultimateArray;
                case 3:
                    megaArray.add(MegaStats.machinedramon);
                    megaArray.add(MegaStats.dorugoramon);
                    megaArray.add(MegaStats.jesmon);
                    return megaArray;
                default:
                    return new ArrayList<>();

            }
        }
    }

    //Uses stage parameter from incoming Get request to lookup correct Digimon for combat
    @GetMapping("api/opponents")
    public Opponents opponents(@RequestParam(value = "stage") String stage) {
        ArrayList<Character> opponentsArray = OpponentsByStage(CheckStage(stage));
        return new Opponents(opponentsArray);
    }
}
