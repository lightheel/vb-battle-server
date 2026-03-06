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

    /** Returns the list of characters for the given stage, built from roster entries (thread-safe snapshot). */
    ArrayList<Character> OpponentsByStage(int stage) {
        if (stage < 0 || stage > 3) {
            return new ArrayList<>();
        }
        ArrayList<RosterEntry> roster = RestServiceApplication.getRosterForStage(stage);
        synchronized (roster) {
            ArrayList<Character> list = new ArrayList<>(roster.size());
            for (RosterEntry e : roster) {
                list.add(e.character());
            }
            return list;
        }
    }

    //Uses stage parameter from incoming Get request to lookup correct Digimon for combat
    @GetMapping("api/opponents")
    public Opponents opponents(@RequestParam(value = "stage") String stage) {
        ArrayList<Character> opponentsArray = OpponentsByStage(CheckStage(stage));
        return new Opponents(opponentsArray);
    }
}