package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);

        //Setup server references for Digi stats
        VBRookieStats.rookieArray.add(VBRookieStats.dim012_mon03); //agumon from agumon EX
        VBRookieStats.rookieArray.add(VBRookieStats.dim129_mon04); //dracomon from draconic blaze
        VBRookieStats.rookieArray.add(VBRookieStats.dim129_mon05); //huckmon from draconic blaze
        VBRookieStats.rookieArray.add(VBRookieStats.dim000_mon03); //pulsemon from impulse city
        VBRookieStats.rookieArray.add(VBRookieStats.dim137_mon03); //dorumon from dorumon

        VBChampionStats.championArray.add(VBChampionStats.dim129_mon07); //coredramon blue from draconic blaze
        VBChampionStats.championArray.add(VBChampionStats.dim129_mon08); //bao hackmon from draconic blaze
        VBChampionStats.championArray.add(VBChampionStats.dim137_mon05); //dorugamon from dorumon
        VBChampionStats.championArray.add(VBChampionStats.dim023_mon04); //betelgammamon from gammamon
        VBChampionStats.championArray.add(VBChampionStats.dim012_mon04); //greymon from agumon EX

        VBUltimateStats.ultimateArray.add(VBUltimateStats.dim129_mon11); //wingdramon from draconic blaze
        VBUltimateStats.ultimateArray.add(VBUltimateStats.dim129_mon12); //savior huckmon from draconic blaze
        VBUltimateStats.ultimateArray.add(VBUltimateStats.dim137_mon09); //dorugreymon from dorumon
        VBUltimateStats.ultimateArray.add(VBUltimateStats.dim012_mon08); //metal greymon (vaccine) from agumon EX

        VBMegaStats.megaArray.add(VBMegaStats.dim129_mon15); //slayerdramon from draconic blaze
        VBMegaStats.megaArray.add(VBMegaStats.dim129_mon16); //jesmon from draconic blaze
        VBMegaStats.megaArray.add(VBMegaStats.dim129_mon17); //breakdramon from draconic blaze
        VBMegaStats.megaArray.add(VBMegaStats.dim137_mon14); //dorugoramon from dorumon
        VBMegaStats.megaArray.add(VBMegaStats.dim137_mon18); //machinedramon from dorumon
    }

}