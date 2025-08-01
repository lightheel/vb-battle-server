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

    /*
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
                rookieArray.add(VBRookieStats.dim012_mon03); //agumon from agumon EX
                rookieArray.add(VBRookieStats.dim000_mon03); //pulsemon from impulse city
                rookieArray.add(VBRookieStats.dim137_mon03); //dorumon from dorumon
                return rookieArray;
                case 1:
                    championArray.add(VBChampionStats.dim012_mon04); //greymon from agumon EX
                    championArray.add(VBChampionStats.dim023_mon04); //betelgammamon from gammamon
                    championArray.add(VBChampionStats.dim137_mon05); //dorugamon from dorumon
                    return championArray;
                    case 2:
                    ultimateArray.add(VBUltimateStats.dim137_mon09); //dorugreymon from dorumon
                    ultimateArray.add(VBUltimateStats.dim012_mon08); //metal greymon (vaccine) from agumon EX
                    ultimateArray.add(VBUltimateStats.dim129_mon11); //wingdramon from draconic blaze
                    return ultimateArray;
                    case 3:
                    megaArray.add(VBMegaStats.dim137_mon18); //machinedramon from dorumon
                    megaArray.add(VBMegaStats.dim137_mon14); //dorugoramon from dorumon
                    megaArray.add(VBMegaStats.dim129_mon16); //jesmon from draconic blaze
                    return megaArray;
                    default:
                    return new ArrayList<>();
                    
                }
            }
        }
        */
        boolean rookieInitialized = false;
        boolean championInitialized = false;
        boolean ultimateInitialized = false;
        boolean megaInitialized = false;
        
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
            switch (stage) {
                case 0:
                    if (!rookieInitialized) {
                        rookieInitialized = true;
                        rookieArray.add(VBRookieStats.dim012_mon03); //agumon from agumon EX
                        rookieArray.add(VBRookieStats.dim000_mon03); //pulsemon from impulse city
                        rookieArray.add(VBRookieStats.dim137_mon03); //dorumon from dorumon
                    }
                    return rookieArray;
                case 1:
                    if (!championInitialized) {
                        championInitialized = true;
                        championArray.add(VBChampionStats.dim012_mon04); //greymon from agumon EX
                        championArray.add(VBChampionStats.dim023_mon04); //betelgammamon from gammamon
                        championArray.add(VBChampionStats.dim137_mon05); //dorugamon from dorumon
                    }
                    return championArray;
                case 2:
                    if (!ultimateInitialized) {
                        ultimateInitialized = true;
                        ultimateArray.add(VBUltimateStats.dim137_mon09); //dorugreymon from dorumon
                        ultimateArray.add(VBUltimateStats.dim012_mon08); //metal greymon (vaccine) from agumon EX
                        ultimateArray.add(VBUltimateStats.dim129_mon11); //wingdramon from draconic blaze
                    }
                    return ultimateArray;
                case 3:
                    if (!megaInitialized) {
                        megaInitialized = true;
                        megaArray.add(VBMegaStats.dim137_mon18); //machinedramon from dorumon
                        megaArray.add(VBMegaStats.dim137_mon14); //dorugoramon from dorumon
                        megaArray.add(VBMegaStats.dim129_mon16); //jesmon from draconic blaze
                    }
                    return megaArray;
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
