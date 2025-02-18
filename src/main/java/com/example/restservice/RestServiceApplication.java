package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);

        //Setup server references for Digi stats
        RookieStats.rookieArray.add(RookieStats.agumon);
        RookieStats.rookieArray.add(RookieStats.dracomon);
        RookieStats.rookieArray.add(RookieStats.hackmon);
        RookieStats.rookieArray.add(RookieStats.pulsemon);
        RookieStats.rookieArray.add(RookieStats.dorumon);

        ChampionStats.championArray.add(ChampionStats.coredramon_blue);
        ChampionStats.championArray.add(ChampionStats.baoHackmon);
        ChampionStats.championArray.add(ChampionStats.dorugamon);
        ChampionStats.championArray.add(ChampionStats.betelGammamon);
        ChampionStats.championArray.add(ChampionStats.greymon);

        UltimateStats.ultimateArray.add(UltimateStats.wingdramon);
        UltimateStats.ultimateArray.add(UltimateStats.saviorHackmon);
        UltimateStats.ultimateArray.add(UltimateStats.doruguremon);
        UltimateStats.ultimateArray.add(UltimateStats.metalGreymon);

        MegaStats.megaArray.add(MegaStats.slayerdramon);
        MegaStats.megaArray.add(MegaStats.jesmon);
        MegaStats.megaArray.add(MegaStats.breakdramon);
        MegaStats.megaArray.add(MegaStats.dorugoramon);
        MegaStats.megaArray.add(MegaStats.machinedramon);
    }

}