package com.example.restservice;

import com.example.vb_battle_server.Character;

import java.util.ArrayList;

public class ChampionStats {

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
    //VB Champion Characters (Phase 4) - (ipId=1 only)

    //DIM CHARACTERS
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Impulse City
    static public final Character dim000_mon04 = new Character("BULKMON","degimon_name_Dim000_004","dim000_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim000_mon05 = new Character("EXERMON","degimon_name_Dim000_005","dim000_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim000_mon06 = new Character("RUNNERMON","degimon_name_Dim000_006","dim000_mon06",1,2,2000, 2000, 2400.0f,600.0f);
    static public final Character dim000_mon07 = new Character("NAMAKEMON","degimon_name_Dim000_007","dim000_mon07",1,2,1600, 1600, 1800.0f,600.0f);
    //Titan of Dust
    static public final Character dim001_mon04 = new Character("TORTOMON","degimon_name_Dim001_004","dim001_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim001_mon05 = new Character("TOGEMON","degimon_name_Dim001_005","dim001_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim001_mon06 = new Character("GOLEMON","degimon_name_Dim001_006","dim001_mon06",1,2,1600, 1600, 1800.0f,600.0f);
    static public final Character dim001_mon07 = new Character("DIGMON","degimon_name_Dim001_007","dim001_mon07",1,4,2000, 2000, 2400.0f,600.0f);
    //Infinite Tide
    static public final Character dim002_mon04 = new Character("TOBIUMON","degimon_name_Dim002_004","dim002_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim002_mon05 = new Character("SEADRAMON","degimon_name_Dim002_005","dim002_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim002_mon06 = new Character("SHELLMON","degimon_name_Dim002_006","dim002_mon06",1,3,1600, 1600, 1800.0f,600.0f);
    static public final Character dim002_mon07 = new Character("OCTOMON","degimon_name_Dim002_007","dim002_mon07",1,2,2000, 2000, 2400.0f,600.0f);
    //Hermit in the Jungle
    static public final Character dim003_mon04 = new Character("SNIMON","degimon_name_Dim003_004","dim003_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim003_mon05 = new Character("SUNFLOWMON","degimon_name_Dim003_005","dim003_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim003_mon06 = new Character("PARASAURMON","degimon_name_Dim003_006","dim003_mon06",1,2,2000, 2000, 2400.0f,600.0f);
    static public final Character dim003_mon07 = new Character("VEGIEMON","degimon_name_Dim003_007","dim003_mon07",1,2,1600, 1600, 1800.0f,600.0f);
    //Primeval Warriors
    static public final Character dim004_mon04 = new Character("STINGMON","degimon_name_Dim004_008","dim004_mon04",1,4,2000, 2000, 3000.0f,900.0f);
    static public final Character dim004_mon05 = new Character("SHADRAMON","degimon_name_Dim004_009","dim004_mon05",1,4,2000, 2000, 2400.0f,600.0f);
    static public final Character dim004_mon06 = new Character("HUDIEMON","degimon_name_Dim004_010","dim004_mon06",1,4,1600, 1600, 2400.0f,900.0f);
    static public final Character dim004_mon07 = new Character("ROACHMON","degimon_name_Dim004_011","dim004_mon07",1,2,1600, 1600, 1800.0f,600.0f);
    //Nu Metal Empire
    static public final Character dim005_mon04 = new Character("MACHMON","degimon_name_Dim005_004","dim005_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim005_mon05 = new Character("BALLISTAMON","degimon_name_Dim005_005","dim005_mon05",1,1,1600, 1600, 2400.0f,900.0f);
    static public final Character dim005_mon06 = new Character("MAILBIRDRAMON","degimon_name_Dim005_006","dim005_mon06",1,3,2000, 2000, 2400.0f,600.0f);
    static public final Character dim005_mon07 = new Character("RAREMON","degimon_name_Dim005_007","dim005_mon07",1,2,1600, 1600, 1800.0f,600.0f);
    //Agnimon EX
    static public final Character dim006_mon05 = new Character("AGUNIMON","degimon_name_Dim006_007","dim006_mon05",1,4,2000, 2000, 3000.0f,900.0f);
    static public final Character dim006_mon06 = new Character("MERAMON","degimon_name_Dim006_003","dim006_mon06",1,3,1600, 1600, 1800.0f,600.0f);
    static public final Character dim006_mon07 = new Character("STARMON","degimon_name_Dim006_008","dim006_mon07",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim006_mon08 = new Character("GRUMBLEMON","degimon_name_Dim006_009","dim006_mon08",1,4,2000, 2000, 2400.0f,600.0f);
    //Ancient Warriors
    static public final Character dim007_mon04 = new Character("VEEDRAMON","degimon_name_Dim007_004","dim007_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim007_mon05 = new Character("EXVEEMON","degimon_name_Dim007_005","dim007_mon05",1,4,1600, 1600, 2400.0f,900.0f);
    static public final Character dim007_mon06 = new Character("FLAMEDRAMON","degimon_name_Dim007_006","dim007_mon06",1,4,2000, 2000, 2400.0f,600.0f);
    static public final Character dim007_mon07 = new Character("LIGHDRAMON","degimon_name_Dim007_007","dim007_mon07",1,4,1600, 1600, 1800.0f,600.0f);
    //Volcanic Beat
    static public final Character dim008_mon04 = new Character("BIRDRAMON","degimon_name_Dim008_004","dim008_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim008_mon05 = new Character("MERAMON","degimon_name_Dim008_005","dim008_mon05",1,3,1600, 1600, 1800.0f,600.0f);
    static public final Character dim008_mon06 = new Character("TYRANNOMON","degimon_name_Dim008_006","dim008_mon06",1,3,2000, 2000, 2400.0f,600.0f);
    static public final Character dim008_mon07 = new Character("LAVORVOMON","degimon_name_Dim008_007","dim008_mon07",1,2,1600, 1600, 2400.0f,900.0f);
    //Blizard Fang
    static public final Character dim009_mon04 = new Character("FRIGIMON","degimon_name_Dim009_004","dim009_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim009_mon05 = new Character("PALEDRAMON","degimon_name_Dim009_005","dim009_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim009_mon06 = new Character("HYOGAMON","degimon_name_Dim009_006","dim009_mon06",1,2,2000, 2000, 2400.0f,600.0f);
    static public final Character dim009_mon07 = new Character("MOOSEMON","degimon_name_Dim009_007","dim009_mon07",1,4,1600, 1600, 1800.0f,600.0f);
    //Fairimon EX
    static public final Character dim010_mon05 = new Character("KAZEMON","degimon_name_Dim010_005","dim010_mon05",1,4,2000, 2000, 3000.0f,900.0f);
    static public final Character dim010_mon06 = new Character("KOKATORIMON","degimon_name_Dim010_006","dim010_mon06",1,3,2000, 2000, 2400.0f,600.0f);
    static public final Character dim010_mon07 = new Character("ARBORMON","degimon_name_Dim010_007","dim010_mon07",1,4,1600, 1600, 2400.0f,900.0f);
    static public final Character dim010_mon08 = new Character("WEEDMON","degimon_name_Dim010_008","dim010_mon08",1,2,1600, 1600, 1800.0f,600.0f);
    //Dynasty of the Evil
    static public final Character dim011_mon04 = new Character("SABERDRAMON","degimon_name_Dim011_007","dim011_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim011_mon05 = new Character("WIZARDMON","degimon_name_Dim011_001","dim011_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim011_mon06 = new Character("DEVIMON","degimon_name_Dim011_008","dim011_mon06",1,2,2000, 2000, 2400.0f,600.0f);
    static public final Character dim011_mon07 = new Character("BAKEMON","degimon_name_Dim011_009","dim011_mon07",1,2,1600, 1600, 1800.0f,600.0f);
    //Agumon EX
    static public final Character dim012_mon04 = new Character("GREYMON","degimon_name_Dim012_004","dim012_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim012_mon05 = new Character("COELAMON","degimon_name_Dim012_005","dim012_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim012_mon06 = new Character("OGREMON","degimon_name_Dim012_006","dim012_mon06",1,2,2000, 2000, 2400.0f,600.0f);
    static public final Character dim012_mon07 = new Character("ARGOMON","degimon_name_Dim012_007","dim012_mon07",1,2,1600, 1600, 1800.0f,600.0f);
    //Gabumon EX
    static public final Character dim013_mon04 = new Character("GARURUMON","degimon_name_Dim013_006","dim013_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim013_mon05 = new Character("GORILLAMON","degimon_name_Dim013_007","dim013_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim013_mon06 = new Character("DOKUGUMON","degimon_name_Dim013_008","dim013_mon06",1,2,2000, 2000, 2400.0f,600.0f);
    static public final Character dim013_mon07 = new Character("ARGOMON","degimon_name_Dim013_001","dim013_mon07",1,2,1600, 1600, 1800.0f,600.0f);
    //Black Roar
    static public final Character dim014_mon04 = new Character("GREYMON (BLUE)","degimon_name_Dim014_004","dim014_mon04",1,2,2000, 2000, 3000.0f,900.0f);
    //Shadow Howl
    static public final Character dim015_mon04 = new Character("GARURUMON (BLACK)","degimon_name_Dim015_005","dim015_mon04",1,2,2000, 2000, 3000.0f,900.0f);
    //Chackmon EX
    static public final Character dim016_mon05 = new Character("KUMAMON","degimon_name_Dim016_008","dim016_mon05",1,4,2000, 2000, 3000.0f,900.0f);
    static public final Character dim016_mon06 = new Character("GRIZZLYMON","degimon_name_Dim016_009","dim016_mon06",1,1,2000, 2000, 2400.0f,600.0f);
    static public final Character dim016_mon07 = new Character("ICEMON","degimon_name_Dim016_010","dim016_mon07",1,3,1600, 1600, 1800.0f,600.0f);
    static public final Character dim016_mon08 = new Character("MOJYAMON","degimon_name_Dim016_011","dim016_mon08",1,1,1600, 1600, 2400.0f,900.0f);
    //Guilmon EX
    static public final Character dim017_mon04 = new Character("SEASARMON","degimon_name_Dim017_007","dim017_mon04",1,1,1600, 1600, 2400.0f,900.0f);
    static public final Character dim017_mon05 = new Character("GROWLMON (ORANGE)","degimon_name_Dim017_008","dim017_mon05",1,3,2000, 2000, 3000.0f,600.0f);
    static public final Character dim017_mon06 = new Character("GROWLMON","degimon_name_Dim017_004","dim017_mon06",1,2,1600, 1600, 3000.0f,900.0f);
    static public final Character dim017_mon07 = new Character("DEVIDRAMON","degimon_name_Dim017_009","dim017_mon07",1,2,1600, 1600, 1800.0f,900.0f);
    //Terriermon EX
    static public final Character dim018_mon04 = new Character("GARGOMON","degimon_name_Dim018_004","dim018_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim018_mon05 = new Character("TURUIEMON","degimon_name_Dim018_005","dim018_mon05",1,3,2000, 2000, 2400.0f,600.0f);
    static public final Character dim018_mon06 = new Character("HARPYMON","degimon_name_Dim018_006","dim018_mon06",1,4,1600, 1600, 1800.0f,600.0f);
    static public final Character dim018_mon07 = new Character("WENDIGOMON","degimon_name_Dim018_007","dim018_mon07",1,2,1600, 1600, 2400.0f,900.0f);
    //Guilmon GP
    static public final Character dim019_mon04 = new Character("GROWLMON","degimon_name_Dim019_004","dim019_mon04",1,2,1600, 1600, 3000.0f,900.0f);
    //Impmon GP
    static public final Character dim020_mon04 = new Character("WIZARDMON","degimon_name_Dim020_004","dim020_mon04",1,3,1600, 1600, 2400.0f,900.0f);
    //Renamon GP
    static public final Character dim021_mon04 = new Character("KYUBIMON","degimon_name_Dim021_004","dim021_mon04",1,3,1600, 1600, 2400.0f,900.0f);
    //Monodramon GP
    static public final Character dim022_mon04 = new Character("STRIKEDRAMON","degimon_name_Dim022_012","dim022_mon04",1,1,1600, 1600, 2400.0f,900.0f);
    //Gammamon
    static public final Character dim023_mon04 = new Character("BETELGAMMAMON","degimon_name_Dim023_004","dim023_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim023_mon05 = new Character("KAUSGAMMAMON","degimon_name_Dim023_005","dim023_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim023_mon06 = new Character("WEZENGAMMAMON","degimon_name_Dim023_006","dim023_mon06",1,3,1600, 1600, 1800.0f,600.0f);
    static public final Character dim023_mon07 = new Character("GULUSGAMMAMON","degimon_name_Dim023_007","dim023_mon07",1,2,2000, 2000, 2400.0f,900.0f);
    //Angoramon
    static public final Character dim025_mon04 = new Character("SYMBAREANGORAMON","degimon_name_Dim025_004","dim025_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim025_mon05 = new Character("LEOMON","degimon_name_Dim025_005","dim025_mon05",1,1,1600, 1600, 2400.0f,900.0f);
    static public final Character dim025_mon06 = new Character("KOMONDOMON","degimon_name_Dim025_006","dim025_mon06",1,3,1600, 1600, 1800.0f,600.0f);
    static public final Character dim025_mon07 = new Character("PORCUPAMON","degimon_name_Dim025_007","dim025_mon07",1,2,2000, 2000, 2400.0f,900.0f);
    //Jellymon
    static public final Character dim026_mon04 = new Character("DOLPHMON","degimon_name_Dim026_004","dim026_mon04",1,1,2000, 2000, 2400.0f,900.0f);
    static public final Character dim026_mon05 = new Character("TESLAJELLYMON","degimon_name_Dim026_005","dim026_mon05",1,3,1600, 1600, 3000.0f,900.0f);
    static public final Character dim026_mon06 = new Character("GESOMON","degimon_name_Dim026_006","dim026_mon06",1,2,1600, 1600, 1800.0f,600.0f);
    static public final Character dim026_mon07 = new Character("MANBOMON","degimon_name_Dim026_007","dim026_mon07",1,4,2000, 2000, 2400.0f,900.0f);
    //Mad Black Roar
    static public final Character dim027_mon04 = new Character("GREYMON","degimon_name_Dim027_003","dim027_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim027_mon05 = new Character("MONOCHROMON","degimon_name_Dim027_009","dim027_mon05",1,3,2000, 2000, 2400.0f,600.0f);
    static public final Character dim027_mon06 = new Character("GREYMON (BLUE)","degimon_name_Dim027_005","dim027_mon06",1,2,2000, 2000, 3000.0f,900.0f);
    static public final Character dim027_mon07 = new Character("DARKTYRANNOMON","degimon_name_Dim027_010","dim027_mon07",1,2,1600, 1600, 2400.0f,900.0f);
    //True Shadow Howl
    static public final Character dim028_mon04 = new Character("GARURUMON","degimon_name_Dim028_003","dim028_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim028_mon05 = new Character("DRIMOGEMON","degimon_name_Dim028_009","dim028_mon05",1,3,2000, 2000, 2400.0f,600.0f);
    static public final Character dim028_mon06 = new Character("DORULUMON","degimon_name_Dim028_010","dim028_mon06",1,2,1600, 1600, 2400.0f,900.0f);
    static public final Character dim028_mon07 = new Character("GARURUMON (BLACK)","degimon_name_Dim028_006","dim028_mon07",1,2,2000, 2000, 3000.0f,900.0f);
    //Dinosaur Roar
    static public final Character dim029_mon05 = new Character("GREYMON","degimon_name_Dim029_004","dim029_mon05",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim029_mon06 = new Character("MONOCHROMON","degimon_name_Dim029_013","dim029_mon06",1,3,2000, 2000, 2400.0f,600.0f);
    static public final Character dim029_mon07 = new Character("GREYMON (BLUE)","degimon_name_Dim029_009","dim029_mon07",1,2,2000, 2000, 3000.0f,900.0f);
    static public final Character dim029_mon08 = new Character("DARKTYRANNOMON","degimon_name_Dim029_014","dim029_mon08",1,2,1600, 1600, 2400.0f,900.0f);
    //Wolf Howl
    static public final Character dim030_mon05 = new Character("GARURUMON","degimon_name_Dim030_005","dim030_mon05",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim030_mon06 = new Character("DRIMOGEMON","degimon_name_Dim030_013","dim030_mon06",1,3,2000, 2000, 2400.0f,600.0f);
    static public final Character dim030_mon07 = new Character("DORULUMON","degimon_name_Dim030_014","dim030_mon07",1,2,1600, 1600, 2400.0f,900.0f);
    static public final Character dim030_mon08 = new Character("GARURUMON (BLACK)","degimon_name_Dim030_010","dim030_mon08",1,2,2000, 2000, 3000.0f,900.0f);
    //Medarot
    static public final Character dim031_mon04 = new Character("GREYMON","degimon_name_Dim031_001","dim031_mon04",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim031_mon05 = new Character("GARURUMON","degimon_name_Dim031_002","dim031_mon05",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim031_mon06 = new Character("METABEE","degimon_name_Dim031_006","dim031_mon06",1,4,2400, 2400, 2400.0f,600.0f);
    static public final Character dim031_mon07 = new Character("ROKUSHO","degimon_name_Dim031_007","dim031_mon07",1,4,2400, 2400, 2400.0f,600.0f);
    //Renamon EX
    static public final Character dim032_mon04 = new Character("KYUBIMON","degimon_name_Dim032_001","dim032_mon04",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim032_mon05 = new Character("YOUKOMON","degimon_name_Dim032_004","dim032_mon05",1,3,1600, 1600, 2400.0f,900.0f);
    static public final Character dim032_mon06 = new Character("ICEDEVIMON","degimon_name_Dim032_005","dim032_mon06",1,2,1600, 1600, 1800.0f,600.0f);
    static public final Character dim032_mon07 = new Character("LYNXMON","degimon_name_Dim032_006","dim032_mon07",1,4,2000, 2000, 2400.0f,600.0f);
    //Louwemon EX
    static public final Character dim033_mon05 = new Character("LOWEEMON","degimon_name_Dim033_007","dim033_mon05",1,4,2000, 2000, 3000.0f,900.0f);
    static public final Character dim033_mon06 = new Character("LIAMON","degimon_name_Dim033_008","dim033_mon06",1,1,2000, 2000, 2400.0f,600.0f);
    static public final Character dim033_mon07 = new Character("DUSKMON","degimon_name_Dim033_009","dim033_mon07",1,4,1600, 1600, 2400.0f,900.0f);
    static public final Character dim033_mon08 = new Character("MADLEOMON","degimon_name_Dim033_010","dim033_mon08",1,2,1600, 1600, 1800.0f,600.0f);
    //Ryudamon
    static public final Character dim034_mon05 = new Character("ZUBAEAGERMON","degimon_name_Dim034_006","dim034_mon05",1,1,2000, 2000, 3000.0f,900.0f);
    static public final Character dim034_mon06 = new Character("GINRYUMON","degimon_name_Dim034_007","dim034_mon06",1,1,1600, 1600, 2400.0f,900.0f);
    static public final Character dim034_mon07 = new Character("MUSYAMON","degimon_name_Dim034_008","dim034_mon07",1,2,1600, 1600, 2400.0f,900.0f);
    static public final Character dim034_mon08 = new Character("DOKUGUMON","degimon_name_Dim034_001","dim034_mon08",1,2,2000, 2000, 2400.0f,600.0f);
    //Espimon
    static public final Character dim035_mon05 = new Character("TIALUDOMON","degimon_name_Dim035_007","dim035_mon05",1,3,2000, 2000, 3000.0f,900.0f);
    static public final Character dim035_mon06 = new Character("HOVERESPIMON","degimon_name_Dim035_008","dim035_mon06",1,2,1600, 1600, 2400.0f,900.0f);
    static public final Character dim035_mon07 = new Character("REPPAMON","degimon_name_Dim035_009","dim035_mon07",1,1,1600, 1600, 2400.0f,900.0f);
    static public final Character dim035_mon08 = new Character("BLACKTAILMON UVER.","degimon_name_Dim035_010","dim035_mon08",1,2,2000, 2000, 2400.0f,600.0f);
    //Wolfmon EX
    static public final Character dim036_mon05 = new Character("LOBOMON","degimon_name_Dim036_007","dim036_mon05",1,4,2000, 2000, 3000.0f,900.0f);
    static public final Character dim036_mon06 = new Character("DOBERMON","degimon_name_Dim036_008","dim036_mon06",1,1,2000, 2000, 2400.0f,600.0f);
    static public final Character dim036_mon07 = new Character("LANAMON","degimon_name_Dim036_009","dim036_mon07",1,4,1600, 1600, 2400.0f,900.0f);
    static public final Character dim036_mon08 = new Character("EBIDRAMON","degimon_name_Dim036_010","dim036_mon08",1,3,1600, 1600, 1800.0f,600.0f);
    //Blitzmon EX
    static public final Character dim037_mon05 = new Character("BEETLEMON","degimon_name_Dim037_005","dim037_mon05",1,4,2000, 2000, 3000.0f,900.0f);
    static public final Character dim037_mon06 = new Character("THUNDERMON","degimon_name_Dim037_006","dim037_mon06",1,3,1600, 1600, 1800.0f,600.0f);
    static public final Character dim037_mon07 = new Character("MERCURYMON","degimon_name_Dim037_007","dim037_mon07",1,4,1600, 1600, 2400.0f,900.0f);
    static public final Character dim037_mon08 = new Character("BLADEKUWAGAMON","degimon_name_Dim037_008","dim037_mon08",1,2,2000, 2000, 2400.0f,600.0f);

    //BEM CHARACTERS
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    //25th Anniversary Characters
    static public final Character dim124_mon05 = new Character("GREYMON","degimon_name_dim124_mon05","dim124_mon05",1,1,3500, 3500, 5100.0f,1300.0f);
    static public final Character dim124_mon06 = new Character("AIRDRAMON","degimon_name_dim124_mon06","dim124_mon06",1,1,3600, 3600, 5500.0f,1160.0f);
    static public final Character dim124_mon07 = new Character("TYRANNOMON","degimon_name_dim124_mon07","dim124_mon07",1,3,3700, 3700, 4400.0f,1350.0f);
    static public final Character dim124_mon08 = new Character("SEADRAMON","degimon_name_dim124_mon08","dim124_mon08",1,3,4000, 4000, 4700.0f,1260.0f);
    static public final Character dim124_mon09 = new Character("MERAMON","degimon_name_dim124_mon09","dim124_mon09",1,3,3600, 3600, 5000.0f,1260.0f);
    static public final Character dim124_mon10 = new Character("DEVIMON","degimon_name_dim124_mon10","dim124_mon10",1,2,3200, 3200, 5200.0f,1300.0f);
    static public final Character dim124_mon11 = new Character("NUMEMON","degimon_name_dim124_mon11","dim124_mon11",1,2,3400, 3400, 4700.0f,1160.0f);
    static public final Character dim124_mon21 = new Character("BULKMON","degimon_name_dim124_mon21","dim124_mon21",1,1,3600, 3600, 5200.0f,1300.0f);
    //Gammamon
    static public final Character dim125_mon04 = new Character("BETELGAMMAMON","degimon_name_dim125_mon04","dim125_mon04",1,1,3600, 3600, 5200.0f,1200.0f);
    static public final Character dim125_mon05 = new Character("KAUSGAMMAMON","degimon_name_dim125_mon05","dim125_mon05",1,3,3400, 3400, 5100.0f,1250.0f);
    static public final Character dim125_mon06 = new Character("WEZENGAMMAMON","degimon_name_dim125_mon06","dim125_mon06",1,3,3500, 3500, 4800.0f,1300.0f);
    static public final Character dim125_mon07 = new Character("GULUSGAMMAMON","degimon_name_dim125_mon07","dim125_mon07",1,2,3700, 3700, 5300.0f,1250.0f);
    static public final Character dim125_mon20 = new Character("SEALSDRAMON","degimon_name_dim125_mon20","dim125_mon20",1,2,3500, 3500, 5300.0f,1250.0f);
    //Imperialdramon
    static public final Character dim128_mon04 = new Character("EXVEEMON","degimon_name_dim128_mon04","dim128_mon04",1,4,3500, 3500, 5000.0f,1300.0f);
    static public final Character dim128_mon10 = new Character("STINGMON","degimon_name_dim128_mon10","dim128_mon10",1,4,3800, 3800, 5100.0f,1200.0f);
    //Angoramon
    static public final Character dim126_mon04 = new Character("SYMBAREANGORAMON","degimon_name_dim126_mon04","dim126_mon04",1,1,3500, 3500, 5500.0f,1250.0f);
    static public final Character dim126_mon05 = new Character("SEASARMON","degimon_name_dim126_mon05","dim126_mon05",1,1,3200, 3200, 5100.0f,1200.0f);
    static public final Character dim126_mon06 = new Character("DOGGYMON","degimon_name_dim126_mon06","dim126_mon06",1,3,3700, 3700, 4800.0f,1300.0f);
    static public final Character dim126_mon07 = new Character("SANGLOUPMON","degimon_name_dim126_mon07","dim126_mon07",1,2,3000, 3000, 5800.0f,1250.0f);
    static public final Character dim126_mon19 = new Character("PUBLIMON","degimon_name_dim126_mon19","dim126_mon19",1,3,3100, 3100, 5200.0f,1250.0f);
    //Jellymon
    static public final Character dim127_mon04 = new Character("IKKAKUMON","degimon_name_dim127_mon04","dim127_mon04",1,1,3700, 3700, 5500.0f,1250.0f);
    static public final Character dim127_mon05 = new Character("TOBIUMON","degimon_name_dim127_mon05","dim127_mon05",1,1,3600, 3600, 5600.0f,1200.0f);
    static public final Character dim127_mon06 = new Character("TESLAJELLYMON","degimon_name_dim127_mon06","dim127_mon06",1,3,3400, 3400, 5800.0f,1200.0f);
    static public final Character dim127_mon07 = new Character("SHELLNUMEMON","degimon_name_dim127_mon07","dim127_mon07",1,2,3000, 3000, 4800.0f,1150.0f);
    static public final Character dim127_mon19 = new Character("CHAMBLEMON","degimon_name_dim127_mon19","dim127_mon19",1,2,3600, 3600, 5100.0f,1200.0f);
    static public final Character dim127_mon20 = new Character("KINKAKUMON","degimon_name_dim127_mon20","dim127_mon20",1,2,3700, 3700, 4800.0f,1300.0f);
    //Draconic Blaze
    static public final Character dim129_mon06 = new Character("GEOGREYMON","degimon_name_dim129_mon06","dim129_mon06",1,1,3600, 3600, 3800.0f,1300.0f);
    static public final Character dim129_mon07 = new Character("COREDRAMON (BLUE)","degimon_name_dim129_mon07","dim129_mon07",1,1,3300, 3300, 5800.0f,1100.0f);
    static public final Character dim129_mon08 = new Character("BAOHUCKMON","degimon_name_dim129_mon08","dim129_mon08",1,3,3500, 3500, 5600.0f,1300.0f);
    static public final Character dim129_mon09 = new Character("COREDRAMON (GREEN)","degimon_name_dim129_mon09","dim129_mon09",1,2,3600, 3600, 3500.0f,1250.0f);
    static public final Character dim129_mon21 = new Character("ARRESTERDRAMON","degimon_name_dim129_mon21","dim129_mon21",1,1,3600, 3600, 5700.0f,1250.0f);
    //Rampage of the Beast
    static public final Character dim130_mon06 = new Character("GAOGAMON","degimon_name_dim130_mon06","dim130_mon06",1,3,3400, 3400, 6500.0f,1200.0f);
    static public final Character dim130_mon07 = new Character("FIRAMON","degimon_name_dim130_mon07","dim130_mon07",1,1,3700, 3700, 5000.0f,1310.0f);
    static public final Character dim130_mon08 = new Character("LEKISMON","degimon_name_dim130_mon08","dim130_mon08",1,3,3600, 3600, 5500.0f,1100.0f);
    static public final Character dim130_mon09 = new Character("LEOMON","degimon_name_dim130_mon09","dim130_mon09",1,1,3300, 3300, 5000.0f,1100.0f);
    static public final Character dim130_mon21 = new Character("MADLEOMON","degimon_name_dim130_mon21","dim130_mon21",1,2,3400, 3400, 5500.0f,1300.0f);
    //Loogamon
    static public final Character dim131_mon05 = new Character("LOOGARMON","degimon_name_dim131_mon05","dim131_mon05",1,2,3300, 3300, 5400.0f,1300.0f);
    static public final Character dim131_mon06 = new Character("FILMON","degimon_name_dim131_mon06","dim131_mon06",1,3,3600, 3600, 5200.0f,1200.0f);
    static public final Character dim131_mon07 = new Character("TYRANNOMON","degimon_name_dim131_mon07","dim131_mon07",1,3,3700, 3700, 5000.0f,1200.0f);
    static public final Character dim131_mon08 = new Character("DAMEMON","degimon_name_dim131_mon08","dim131_mon08",1,2,3300, 3300, 4800.0f,1150.0f);
    static public final Character dim131_mon21 = new Character("SNATCHMON","degimon_name_dim131_mon21","dim131_mon21",1,4,3400, 3400, 5300.0f,1250.0f);
    //Holy Wing
    static public final Character dim132_mon06 = new Character("BIRDRAMON","degimon_name_dim132_mon06","dim132_mon06",1,1,3500, 3500, 5500.0f,1200.0f);
    static public final Character dim132_mon07 = new Character("PIDDOMON","degimon_name_dim132_mon07","dim132_mon07",1,1,3700, 3700, 5300.0f,1250.0f);
    static public final Character dim132_mon08 = new Character("PECKMON","degimon_name_dim132_mon08","dim132_mon08",1,1,3400, 3400, 5400.0f,1150.0f);
    static public final Character dim132_mon09 = new Character("XIQUEMON","degimon_name_dim132_mon09","dim132_mon09",1,1,3600, 3600, 5100.0f,1200.0f);
    static public final Character dim132_mon21 = new Character("THUNDERBIRDMON","degimon_name_dim132_mon21","dim132_mon21",1,3,3600, 3600, 5600.0f,1250.0f);
    //Forest Guardian
    static public final Character dim133_mon06 = new Character("KABUTERIMON","degimon_name_dim133_mon06","dim133_mon06",1,1,3800, 3800, 5000.0f,1250.0f);
    static public final Character dim133_mon07 = new Character("SUNFLOWMON","degimon_name_dim133_mon07","dim133_mon07",1,3,3500, 3500, 5600.0f,1300.0f);
    static public final Character dim133_mon08 = new Character("WASPMON","degimon_name_dim133_mon08","dim133_mon08",1,2,3400, 3400, 5400.0f,1200.0f);
    static public final Character dim133_mon09 = new Character("KUWAGAMON","degimon_name_dim133_mon09","dim133_mon09",1,2,3700, 3700, 5250.0f,1200.0f);
    static public final Character dim133_mon21 = new Character("HUDIEMON","degimon_name_dim133_mon21","dim133_mon21",1,4,3600, 3600, 5800.0f,1200.0f);
    //Ryudamon
    static public final Character dim136_mon05 = new Character("GINRYUMON","degimon_name_dim136_mon05","dim136_mon05",1,1,3600, 3600, 5300.0f,1150.0f);
    static public final Character dim136_mon06 = new Character("HI-COMMANDRAMON","degimon_name_dim136_mon06","dim136_mon06",1,2,4000, 4000, 5000.0f,1250.0f);
    static public final Character dim136_mon07 = new Character("DELTAMON","degimon_name_dim136_mon07","dim136_mon07",1,2,3600, 3600, 4800.0f,1300.0f);
    static public final Character dim136_mon08 = new Character("NUMEMON","degimon_name_dim136_mon08","dim136_mon08",1,2,3500, 3500, 4600.0f,1100.0f);
    static public final Character dim136_mon20 = new Character("SEALSDRAMON","degimon_name_dim136_mon20","dim136_mon20",1,2,3500, 3500, 5300.0f,1250.0f);
    //Dorumon
    static public final Character dim137_mon05 = new Character("DORUGAMON","degimon_name_dim137_mon05","dim137_mon05",1,3,3500, 3500, 5200.0f,1200.0f);
    static public final Character dim137_mon06 = new Character("DEXDORUGAMON","degimon_name_dim137_mon06","dim137_mon06",1,2,3200, 3200, 4900.0f,1300.0f);
    static public final Character dim137_mon07 = new Character("DARKTYRANNOMON","degimon_name_dim137_mon07","dim137_mon07",1,2,3300, 3300, 4700.0f,1250.0f);
    static public final Character dim137_mon08 = new Character("AIRDRAMON","degimon_name_dim137_mon08","dim137_mon08",1,1,3200, 3200, 4900.0f,1200.0f);
    static public final Character dim137_mon20 = new Character("RAPTORDRAMON","degimon_name_dim137_mon20","dim137_mon20",1,1,3400, 3400, 5700.0f,1200.0f);
    //D-3 White and Yellow
    static public final Character dim134_mon06 = new Character("ANKYLOMON","degimon_name_dim134_mon06","dim134_mon06",1,4,4000, 4000, 4400.0f,1350.0f);
    static public final Character dim134_mon07 = new Character("ANGEMON","degimon_name_dim134_mon07","dim134_mon07",1,1,3800, 3800, 5500.0f,1300.0f);
    static public final Character dim134_mon08 = new Character("EXVEEMON","degimon_name_dim134_mon08","dim134_mon08",1,4,3500, 3500, 5000.0f,1300.0f);
    static public final Character dim134_mon09 = new Character("DIGMON","degimon_name_dim134_mon09","dim134_mon09",1,4,3500, 3500, 4200.0f,1250.0f);
    static public final Character dim134_mon10 = new Character("SUBMARIMON","degimon_name_dim134_mon10","dim134_mon10",1,4,3300, 3300, 5100.0f,1200.0f);
    static public final Character dim134_mon11 = new Character("PEGASUSMON","degimon_name_dim134_mon11","dim134_mon11",1,4,3300, 3300, 5300.0f,1100.0f);
    static public final Character dim134_mon12 = new Character("FLAMEDRAMON","degimon_name_dim134_mon12","dim134_mon12",1,4,3450, 3450, 4800.0f,1200.0f);
    static public final Character dim134_mon13 = new Character("LIGHDRAMON","degimon_name_dim134_mon13","dim134_mon13",1,4,3400, 3400, 5200.0f,1100.0f);
    static public final Character dim135_mon06 = new Character("AQUILAMON","degimon_name_dim135_mon06","dim135_mon06",1,4,3300, 3300, 5800.0f,1200.0f);
    //D-3 White and Red
    static public final Character dim135_mon07 = new Character("GATOMON","degimon_name_dim135_mon07","dim135_mon07",1,1,3500, 3500, 5600.0f,1150.0f);
    static public final Character dim135_mon08 = new Character("STINGMON","degimon_name_dim135_mon08","dim135_mon08",1,4,3800, 3800, 5100.0f,1200.0f);
    static public final Character dim135_mon09 = new Character("HALSEMON","degimon_name_dim135_mon09","dim135_mon09",1,4,3200, 3200, 5300.0f,1100.0f);
    static public final Character dim135_mon10 = new Character("NEFERTIMON","degimon_name_dim135_mon10","dim135_mon10",1,4,3400, 3400, 5700.0f,1100.0f);
    static public final Character dim135_mon11 = new Character("SHURIMON","degimon_name_dim135_mon11","dim135_mon11",1,4,3100, 3100, 5200.0f,1150.0f);
    //Pulsemon
    static public final Character dim138_mon06 = new Character("BULKMON","degimon_name_dim138_mon06","dim138_mon06",1,1,3600, 3600, 5200.0f,1300.0f);
    static public final Character dim138_mon07 = new Character("REPPAMON","degimon_name_dim138_mon07","dim138_mon07",1,1,3700, 3700, 5000.0f,1200.0f);
    static public final Character dim138_mon08 = new Character("THUNDERMON","degimon_name_dim138_mon08","dim138_mon08",1,3,3300, 3300, 4800.0f,1150.0f);
    static public final Character dim138_mon09 = new Character("BLADEKUWAGAMON","degimon_name_dim138_mon09","dim138_mon09",1,2,3000, 3000, 5000.0f,1100.0f);
    static public final Character dim138_mon20 = new Character("SHOUTMON X4","degimon_name_dim138_mon20","dim138_mon20",1,3,4000, 4000, 5500.0f,1350.0f);
    
    static public ArrayList<Character> championArray = new ArrayList<Character>();
}
