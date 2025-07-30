package com.example.restservice;

import com.example.vb_battle_server.Character;
import java.util.ArrayList;

public class VBRookieStats {

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

    //Digimon server-side stat references.
    //Rookie

    //VB Rookie Characters (Phase 3) - Systematically verified from CharacterData.json (ipId=1 only)
    //Impulse City
    static public final Character dim000_mon03 = new Character("PULSEMON","degimon_name_Dim000_003","dim000_mon03",0,1,1800, 1800, 2400.0f,700.0f);
    //Titan of Dust
    static public final Character dim001_mon03 = new Character("SUNARIZAMON","degimon_name_Dim001_003","dim001_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Infinite Tide
    static public final Character dim002_mon03 = new Character("SANGOMON","degimon_name_Dim002_003","dim002_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    //Hermit in the Jungle
    static public final Character dim003_mon03 = new Character("PALMON","degimon_name_Dim003_003","dim003_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    //Primeval Warriors
    static public final Character dim004_mon03 = new Character("WORMMON","degimon_name_Dim004_007","dim004_mon03",0,4,1800, 1800, 2400.0f,700.0f);
    //Nu Metal Empire
    static public final Character dim005_mon03 = new Character("HAGURUMON","degimon_name_Dim005_003","dim005_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Agnimon EX
    static public final Character dim006_mon03 = new Character("FLAMEMON","degimon_name_Dim006_005","dim006_mon03",0,4,1800, 1800, 2400.0f,700.0f);
    static public final Character dim006_mon04 = new Character("GOTSUMON","degimon_name_Dim006_006","dim006_mon04",0,3,1800, 1800, 2400.0f,700.0f);
    //Ancient Warriors
    static public final Character dim007_mon03 = new Character("VEEMON","degimon_name_Dim007_003","dim007_mon03",0,4,1800, 1800, 2400.0f,700.0f);
    //Volcanic Beat
    static public final Character dim008_mon03 = new Character("VORVOMON","degimon_name_Dim008_003","dim008_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Blizard Fang
    static public final Character dim009_mon03 = new Character("BULUCOMON","degimon_name_Dim009_003","dim009_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    //Fairimon EX
    static public final Character dim010_mon03 = new Character("TINKERMON","degimon_name_Dim010_003","dim010_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    static public final Character dim010_mon04 = new Character("POMUMON","degimon_name_Dim010_004","dim010_mon04",0,3,1800, 1800, 2400.0f,700.0f);
    //Dynasty of the Evil
    static public final Character dim011_mon03 = new Character("GHOSTMON","degimon_name_Dim011_006","dim011_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    //Agumon EX
    static public final Character dim012_mon03 = new Character("AGUMON","degimon_name_Dim012_003","dim012_mon03",0,1,1800, 1800, 2400.0f,700.0f);
    //Gabumon EX
    static public final Character dim013_mon03 = new Character("GABUMON","degimon_name_Dim013_005","dim013_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    //Black Roar
    static public final Character dim014_mon03 = new Character("AGUMON (BLACK)","degimon_name_Dim014_003","dim014_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Shadow Howl
    static public final Character dim015_mon03 = new Character("GABUMON (BLACK)","degimon_name_Dim015_004","dim015_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Chackmon EX
    static public final Character dim016_mon03 = new Character("BEARMON","degimon_name_Dim016_006","dim016_mon03",0,1,1800, 1800, 2400.0f,700.0f);
    static public final Character dim016_mon04 = new Character("SNOWGOBLIMON","degimon_name_Dim016_007","dim016_mon04",0,2,1800, 1800, 2400.0f,700.0f);
    //Guilmon EX
    static public final Character dim017_mon03 = new Character("GUILMON","degimon_name_Dim017_003","dim017_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Terriermon EX
    static public final Character dim018_mon03 = new Character("TERRIERMON","degimon_name_Dim018_003","dim018_mon03",0,1,1800, 1800, 2400.0f,700.0f);
    //Guilmon GP
    static public final Character dim019_mon03 = new Character("GUILMON","degimon_name_Dim019_003","dim019_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Impmon GP
    static public final Character dim020_mon03 = new Character("IMPMON","degimon_name_Dim020_003","dim020_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Renamon GP
    static public final Character dim021_mon03 = new Character("RENAMON","degimon_name_Dim021_003","dim021_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    //Monodramon GP
    static public final Character dim022_mon03 = new Character("MONODRAMON","degimon_name_Dim022_011","dim022_mon03",0,1,1800, 1800, 2400.0f,700.0f);
    //Gammamon
    static public final Character dim023_mon03 = new Character("GAMMAMON","degimon_name_Dim023_003","dim023_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Angoramon
    static public final Character dim025_mon03 = new Character("ANGORAMON","degimon_name_Dim025_003","dim025_mon03",0,1,1800, 1800, 2400.0f,700.0f);
    //Jellymon
    static public final Character dim026_mon03 = new Character("JELLYMON","degimon_name_Dim026_003","dim026_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    //Mad Black Roar
    static public final Character dim027_mon03 = new Character("AGUMON (BLACK)","degimon_name_Dim027_004","dim027_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //True Shadow Howl
    static public final Character dim028_mon03 = new Character("GABUMON (BLACK)","degimon_name_Dim028_005","dim028_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    //Dinosaur Roar
    static public final Character dim029_mon03 = new Character("AGUMON","degimon_name_Dim029_003","dim029_mon03",0,1,1800, 1800, 2400.0f,700.0f);
    static public final Character dim029_mon04 = new Character("AGUMON (BLACK)","degimon_name_Dim029_008","dim029_mon04",0,2,1800, 1800, 2400.0f,700.0f);
    //Wolf Howl
    static public final Character dim030_mon03 = new Character("GABUMON","degimon_name_Dim030_004","dim030_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    static public final Character dim030_mon04 = new Character("GABUMON (BLACK)","degimon_name_Dim030_009","dim030_mon04",0,2,1800, 1800, 2400.0f,700.0f);
    //Medarot
    static public final Character dim031_mon03 = new Character("TINPET","degimon_name_Dim031_005","dim031_mon03",0,4,1800, 1800, 2400.0f,700.0f);
    //Renamon EX
    static public final Character dim032_mon03 = new Character("RENAMON","degimon_name_Dim032_017","dim032_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    //Louwemon EX
    static public final Character dim033_mon03 = new Character("LIOLLMON","degimon_name_Dim033_005","dim033_mon03",0,1,1800, 1800, 2400.0f,700.0f);
    static public final Character dim033_mon04 = new Character("GAZIMON","degimon_name_Dim033_006","dim033_mon04",0,2,1800, 1800, 2400.0f,700.0f);
    //Ryudamon
    static public final Character dim034_mon03 = new Character("ZUBAMON","degimon_name_Dim034_004","dim034_mon03",0,1,1800, 1800, 2400.0f,700.0f);
    static public final Character dim034_mon04 = new Character("RYUDAMON","degimon_name_Dim034_005","dim034_mon04",0,1,1800, 1800, 2400.0f,700.0f);
    //Espimon
    static public final Character dim035_mon03 = new Character("LUDOMON","degimon_name_Dim035_005","dim035_mon03",0,3,1800, 1800, 2400.0f,700.0f);
    static public final Character dim035_mon04 = new Character("ESPIMON","degimon_name_Dim035_006","dim035_mon04",0,2,1800, 1800, 2400.0f,700.0f);
    //Strabimon
    static public final Character dim036_mon03 = new Character("STRABIMON","degimon_name_Dim036_005","dim036_mon03",0,4,1800, 1800, 2400.0f,700.0f);
    static public final Character dim036_mon04 = new Character("GIZAMON","degimon_name_Dim036_006","dim036_mon04",0,2,1800, 1800, 2400.0f,700.0f);
    //Blitzmon EX
    static public final Character dim037_mon03 = new Character("KOKABUTERIMON","degimon_name_Dim037_003","dim037_mon03",0,2,1800, 1800, 2400.0f,700.0f);
    static public final Character dim037_mon04 = new Character("KOKUWAMON","degimon_name_Dim037_004","dim037_mon04",0,3,1800, 1800, 2400.0f,700.0f);

    static public ArrayList<Character> rookieArray = new ArrayList<Character>();
}
