package com.example.restservice;

import com.example.vb_battle_server.Character;

import java.util.ArrayList;

public class MegaStats {

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
    //VB Mega Characters (Phase 3) - (ipId=1 only)

    //DIM CHARACTERS
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Impulse City
    static public final Character dim000_mon14 = new Character("KAZUCHIMON","degimon_name_Dim000_014","dim000_mon14",3,1,3080, 3080, 4250.0f,1120.0f);
    static public final Character dim000_mon15 = new Character("SHIVAMON","degimon_name_Dim000_015","dim000_mon15",3,3,3520, 3520, 3825.0f,960.0f);
    static public final Character dim000_mon16 = new Character("ACHILLESMON","degimon_name_Dim000_016","dim000_mon16",3,2,2640, 2640, 4250.0f,800.0f);
    static public final Character dim000_mon17 = new Character("SHROUDMON","degimon_name_Dim000_017","dim000_mon17",3,2,3520, 3520, 5100.0f,1120.0f);
    //Titan of Dust
    static public final Character dim001_mon14 = new Character("BLASTMON","degimon_name_Dim001_014","dim001_mon14",3,1,3960, 3960, 3825.0f,1120.0f);
    static public final Character dim001_mon15 = new Character("DINOREXMON","degimon_name_Dim001_015","dim001_mon15",3,3,3080, 3080, 4675.0f,1280.0f);
    static public final Character dim001_mon16 = new Character("PHARAOHMON","degimon_name_Dim001_016","dim001_mon16",3,2,2640, 2640, 4250.0f,1280.0f);
    static public final Character dim001_mon17 = new Character("ANCIENTSPHINXMON","degimon_name_Dim001_017","dim001_mon17",3,2,2640, 2640, 5950.0f,1280.0f);
    //Infinite Tide
    static public final Character dim002_mon14 = new Character("NEPTUNEMON","degimon_name_Dim002_014","dim002_mon14",3,1,3520, 3520, 3825.0f,1280.0f);
    static public final Character dim002_mon15 = new Character("METALSEADRAMON","degimon_name_Dim002_015","dim002_mon15",3,3,3960, 3960, 4675.0f,960.0f);
    static public final Character dim002_mon16 = new Character("REGALECUSMON","degimon_name_Dim002_016","dim002_mon16",3,2,3080, 3080, 4250.0f,1280.0f);
    static public final Character dim002_mon17 = new Character("PLESIOMON","degimon_name_Dim002_017","dim002_mon17",3,3,2640, 2640, 5950.0f,1280.0f);
    //Hermit in the Jungle
    static public final Character dim003_mon14 = new Character("BLOOMLORDMON","degimon_name_Dim003_014","dim003_mon14",3,1,3080, 3080, 4675.0f,1280.0f);
    static public final Character dim003_mon15 = new Character("RAFFLESIMON","degimon_name_Dim003_015","dim003_mon15",3,3,2640, 2640, 3825.0f,1120.0f);
    static public final Character dim003_mon16 = new Character("SPINOMON","degimon_name_Dim003_016","dim003_mon16",3,2,3520, 3520, 4250.0f,1120.0f);
    static public final Character dim003_mon17 = new Character("HYDRAMON","degimon_name_Dim003_017","dim003_mon17",3,2,4840, 4840, 4675.0f,960.0f);
    //Primeval Warriors
    static public final Character dim004_mon14 = new Character("BANCHOSTINGMON","degimon_name_Dim004_015","dim004_mon14",3,4,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim004_mon15 = new Character("GRANKUWAGAMON","degimon_name_Dim004_016","dim004_mon15",3,4,3520, 3520, 4250.0f,960.0f);
    static public final Character dim004_mon16 = new Character("IMPERIALDRAMON: DRAGON MODE","degimon_name_Dim004_003","dim004_mon16",3,4,3520, 3520, 3825.0f,960.0f);
    static public final Character dim004_mon17 = new Character("IMPERIALDRAMON: PALADIN MODE","degimon_name_Dim004_017","dim004_mon17",3,1,2640, 2640, 5100.0f,1440.0f);
    //Nu Metal Empire
    static public final Character dim005_mon14 = new Character("HEAVYLEOMON","degimon_name_Dim005_014","dim005_mon14",3,1,3520, 3520, 4250.0f,1120.0f);
    static public final Character dim005_mon15 = new Character("GROUNDLOCOMON","degimon_name_Dim005_015","dim005_mon15",3,3,3960, 3960, 3825.0f,960.0f);
    static public final Character dim005_mon16 = new Character("ZEIGGREYMON","degimon_name_Dim005_016","dim005_mon16",3,2,3080, 3080, 4675.0f,1280.0f);
    static public final Character dim005_mon17 = new Character("MACHINEDRAMON","degimon_name_Dim005_017","dim005_mon17",3,2,3960, 3960, 4675.0f,1280.0f);
    //Agnimon EX
    static public final Character dim006_mon14 = new Character("EMPERORGREYMON","degimon_name_Dim006_015","dim006_mon14",3,4,2200, 2200, 4250.0f,1280.0f);
    static public final Character dim006_mon15 = new Character("ANCIENTGREYMON","degimon_name_Dim006_016","dim006_mon15",3,1,3080, 3080, 4250.0f,1120.0f);
    static public final Character dim006_mon16 = new Character("ANCIENTVOLCANOMON","degimon_name_Dim006_004","dim006_mon16",3,2,3960, 3960, 4675.0f,1120.0f);
    static public final Character dim006_mon17 = new Character("SUSANOMON","degimon_name_Dim006_017","dim006_mon17",3,1,2640, 2640, 5525.0f,1920.0f);
    //Ancient Warriors
    static public final Character dim007_mon14 = new Character("ULFORCEVEEDRAMON","degimon_name_Dim007_014","dim007_mon14",3,1,3080, 3080, 4250.0f,1120.0f);
    static public final Character dim007_mon15 = new Character("IMPERIALDRAMON: DRAGON MODE","degimon_name_Dim007_015","dim007_mon15",3,4,3520, 3520, 3825.0f,960.0f);
    static public final Character dim007_mon16 = new Character("MAGNAMON","degimon_name_Dim007_016","dim007_mon16",3,4,2640, 2640, 4250.0f,800.0f);
    static public final Character dim007_mon17 = new Character("IMPERIALDRAMON: FIGHTER MODE","degimon_name_Dim007_017","dim007_mon17",3,4,3080, 3080, 5100.0f,1280.0f);
    //Volcanic Beat
    static public final Character dim008_mon14 = new Character("MARSMON","degimon_name_Dim008_014","dim008_mon14",3,1,3080, 3080, 4250.0f,1120.0f);
    static public final Character dim008_mon15 = new Character("CANNONDRAMON","degimon_name_Dim008_015","dim008_mon15",3,3,3520, 3520, 3825.0f,960.0f);
    static public final Character dim008_mon16 = new Character("VOLCANICDRAMON","degimon_name_Dim008_016","dim008_mon16",3,2,3080, 3080, 4675.0f,1280.0f);
    static public final Character dim008_mon17 = new Character("ANCIENTVOLCANOMON","degimon_name_Dim008_017","dim008_mon17",3,2,3960, 3960, 4675.0f,1120.0f);
    //Blizard Fang
    static public final Character dim009_mon14 = new Character("SKULLMAMMOTHMON","degimon_name_Dim009_014","dim009_mon14",3,1,2640, 2640, 3825.0f,1280.0f);
    static public final Character dim009_mon15 = new Character("HEXEBLAUMON","degimon_name_Dim009_015","dim009_mon15",3,3,3080, 3080, 4675.0f,1120.0f);
    static public final Character dim009_mon16 = new Character("FROSVELGRMON","degimon_name_Dim009_016","dim009_mon16",3,2,3520, 3520, 4250.0f,1280.0f);
    static public final Character dim009_mon17 = new Character("ANCIENTMEGATHERIUMMON","degimon_name_Dim009_017","dim009_mon17",3,3,3080, 3080, 5100.0f,1120.0f);
    //Fairimon EX
    static public final Character dim010_mon14 = new Character("ANCIENTKAZEMON","degimon_name_Dim010_014","dim010_mon14",3,1,2200, 2200, 4250.0f,1120.0f);
    static public final Character dim010_mon15 = new Character("LOTOSMON","degimon_name_Dim010_015","dim010_mon15",3,3,2640, 2640, 3825.0f,1120.0f);
    static public final Character dim010_mon16 = new Character("ANCIENTTROYMON","degimon_name_Dim010_016","dim010_mon16",3,3,3520, 3520, 4250.0f,960.0f);
    static public final Character dim010_mon17 = new Character("LORDKNIGHTMON","degimon_name_Dim010_017","dim010_mon17",3,2,2640, 2640, 4250.0f,1280.0f);
    //Dynasty of the Evil
    static public final Character dim011_mon14 = new Character("NOBLEPUMPKINMON","degimon_name_Dim011_015","dim011_mon14",3,3,2640, 2640, 3825.0f,960.0f);
    static public final Character dim011_mon15 = new Character("PIEDMON","degimon_name_Dim011_016","dim011_mon15",3,2,3520, 3520, 4675.0f,1120.0f);
    static public final Character dim011_mon16 = new Character("BEELZEMON (2010 ANIME VERSION)","degimon_name_Dim011_003","dim011_mon16",3,2,2640, 2640, 4250.0f,1280.0f);
    static public final Character dim011_mon17 = new Character("NEOVAMDEMON","degimon_name_Dim011_017","dim011_mon17",3,2,3080, 3080, 4675.0f,1440.0f);
    //Agumon EX
    static public final Character dim012_mon14 = new Character("WARGREYMON","degimon_name_Dim012_014","dim012_mon14",3,1,3080, 3080, 3825.0f,800.0f);
    static public final Character dim012_mon15 = new Character("OMEGAMON","degimon_name_Dim012_015","dim012_mon15",3,1,3080, 3080, 5100.0f,1120.0f);
    static public final Character dim012_mon16 = new Character("BLITZGREYMON","degimon_name_Dim012_016","dim012_mon16",3,2,2640, 2640, 4250.0f,960.0f);
    static public final Character dim012_mon17 = new Character("DONEDEVIMON","degimon_name_Dim012_017","dim012_mon17",3,2,3520, 3520, 4675.0f,960.0f);
    //Gabumon EX
    static public final Character dim013_mon14 = new Character("METALGARURUMON","degimon_name_Dim013_015","dim013_mon14",3,3,3080, 3080, 3825.0f,800.0f);
    static public final Character dim013_mon15 = new Character("OMEGAMON","degimon_name_Dim013_002","dim013_mon15",3,1,3080, 3080, 5100.0f,1120.0f);
    static public final Character dim013_mon16 = new Character("CRESGARURUMON","degimon_name_Dim013_016","dim013_mon16",3,3,2640, 2640, 4250.0f,960.0f);
    static public final Character dim013_mon17 = new Character("NIDHOGGMON","degimon_name_Dim013_017","dim013_mon17",3,2,3520, 3520, 4675.0f,960.0f);
    //Black Roar
    static public final Character dim014_mon06 = new Character("BLACKWARGREYMON","degimon_name_Dim014_006","dim014_mon06",3,2,3080, 3080, 3825.0f,800.0f);
    static public final Character dim014_mon07 = new Character("OMEGAMON ZWART","degimon_name_Dim014_007","dim014_mon07",3,1,3080, 3080, 5100.0f,1120.0f);
    //Shadow Howl
    static public final Character dim015_mon06 = new Character("METALGARURUMON (BLACK)","degimon_name_Dim015_007","dim015_mon06",3,2,3080, 3080, 3825.0f,800.0f);
    static public final Character dim015_mon07 = new Character("OMEGAMON ZWART","degimon_name_Dim015_003","dim015_mon07",3,1,3080, 3080, 5100.0f,1120.0f);
    //Chackmon EX
    static public final Character dim016_mon14 = new Character("OLEGMON","degimon_name_Dim016_016","dim016_mon14",3,1,2640, 2640, 3825.0f,960.0f);
    static public final Character dim016_mon15 = new Character("FROSVELGRMON","degimon_name_Dim016_004","dim016_mon15",3,2,3520, 3520, 4250.0f,1280.0f);
    static public final Character dim016_mon16 = new Character("ANCIENTMEGATHERIUMMON","degimon_name_Dim016_005","dim016_mon16",3,3,3080, 3080, 5100.0f,1120.0f);
    static public final Character dim016_mon17 = new Character("DYNASMON","degimon_name_Dim016_017","dim016_mon17",3,3,2640, 2640, 4250.0f,1280.0f);
    //Guilmon EX
    static public final Character dim017_mon14 = new Character("GALLANTMON","degimon_name_Dim017_006","dim017_mon14",3,2,3080, 3080, 4675.0f,1120.0f);
    static public final Character dim017_mon15 = new Character("GALLANTMON (X-ANTIBODY)","degimon_name_Dim017_015","dim017_mon15",3,2,3520, 3520, 4675.0f,1120.0f);
    static public final Character dim017_mon16 = new Character("MEGIDRAMON","degimon_name_Dim017_016","dim017_mon16",3,2,2640, 2640, 5100.0f,1440.0f);
    static public final Character dim017_mon17 = new Character("GALLANTMON: CRIMSON MODE","degimon_name_Dim017_017","dim017_mon17",3,2,3520, 3520, 5950.0f,1440.0f);
    //Terriermon EX
    static public final Character dim018_mon14 = new Character("MEGAGARGOMON","degimon_name_Dim018_014","dim018_mon14",3,1,3960, 3960, 4250.0f,960.0f);
    static public final Character dim018_mon15 = new Character("CHERUBIMON (GOOD)","degimon_name_Dim018_015","dim018_mon15",3,1,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim018_mon16 = new Character("CHERUBIMON (BLACK)","degimon_name_Dim018_016","dim018_mon16",3,1,2200, 2200, 4250.0f,1280.0f);
    static public final Character dim018_mon17 = new Character("RAPIDMON","degimon_name_Dim018_017","dim018_mon17",3,1,2640, 2640, 5100.0f,1440.0f);
    //Guilmon GP
    static public final Character dim019_mon06 = new Character("MEDICALGALLANTMON","degimon_name_Dim019_006","dim019_mon06",3,3,2640, 2640, 4250.0f,1280.0f);
    static public final Character dim019_mon07 = new Character("GALLANTMON","degimon_name_Dim019_007","dim019_mon07",3,2,3080, 3080, 4675.0f,1120.0f);
    static public final Character dim019_mon08 = new Character("CHAOSGALLANTMON","degimon_name_Dim019_008","dim019_mon08",3,2,3080, 3080, 4675.0f,1440.0f);
    //Impmon GP
    static public final Character dim020_mon06 = new Character("BEELZEMON (2010 ANIME VERSION)","degimon_name_Dim020_006","dim020_mon06",3,2,2640, 2640, 4250.0f,1280.0f);
    static public final Character dim020_mon07 = new Character("BEELZEMON","degimon_name_Dim020_007","dim020_mon07",3,2,2640, 2640, 4675.0f,1280.0f);
    static public final Character dim020_mon08 = new Character("BEELZEMON: BLAST MODE","degimon_name_Dim020_008","dim020_mon08",3,2,3080, 3080, 4675.0f,1440.0f);
    //Renamon GP
    static public final Character dim021_mon06 = new Character("SAKUYAMON","degimon_name_Dim021_006","dim021_mon06",3,3,3080, 3080, 4250.0f,1280.0f);
    static public final Character dim021_mon07 = new Character("KUZUHAMON","degimon_name_Dim021_007","dim021_mon07",3,3,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim021_mon08 = new Character("SAKUYAMON: MAID MODE","degimon_name_Dim021_008","dim021_mon08",3,3,2640, 2640, 5100.0f,1120.0f);
    //Monodramon GP
    static public final Character dim022_mon06 = new Character("JUSTIMON: ACCEL ARM","degimon_name_Dim022_014","dim022_mon06",3,1,3080, 3080, 4675.0f,1120.0f);
    static public final Character dim022_mon07 = new Character("JUSTIMON: BLITZ ARM","degimon_name_Dim022_015","dim022_mon07",3,1,3080, 3080, 4675.0f,1280.0f);
    static public final Character dim022_mon08 = new Character("JUSTIMON: CRITICAL ARM","degimon_name_Dim022_016","dim022_mon08",3,1,3080, 3080, 4675.0f,1440.0f);
    //Gammamon
    static public final Character dim023_mon12 = new Character("AEGISDRAMON","degimon_name_Dim023_012","dim023_mon12",3,1,3080, 3080, 4675.0f,1120.0f);
    static public final Character dim023_mon13 = new Character("ULTIMATEBRACHIOMON","degimon_name_Dim023_013","dim023_mon13",3,3,2640, 2640, 4250.0f,960.0f);
    static public final Character dim023_mon14 = new Character("CHAOSDRAMON","degimon_name_Dim023_014","dim023_mon14",3,2,3520, 3520, 4675.0f,960.0f);
    //Angoramon
    static public final Character dim025_mon12 = new Character("ANUBISMON","degimon_name_Dim025_012","dim025_mon12",3,1,3520, 3520, 4675.0f,1120.0f);
    static public final Character dim025_mon13 = new Character("SABERLEOMON","degimon_name_Dim025_013","dim025_mon13",3,3,3080, 3080, 4250.0f,960.0f);
    static public final Character dim025_mon14 = new Character("GRYPHONMON","degimon_name_Dim025_014","dim025_mon14",3,3,3080, 3080, 4675.0f,960.0f);
    //Jellymon
    static public final Character dim026_mon12 = new Character("MARINEANGEMON","degimon_name_Dim026_012","dim026_mon12",3,1,3080, 3080, 4675.0f,1120.0f);
    static public final Character dim026_mon13 = new Character("ANCIENTMERMAIMON","degimon_name_Dim026_013","dim026_mon13",3,3,3300, 3300, 4675.0f,960.0f);
    static public final Character dim026_mon14 = new Character("PUKUMON","degimon_name_Dim026_014","dim026_mon14",3,2,2860, 2860, 4250.0f,960.0f);
    //Mad Black Roar
    static public final Character dim027_mon14 = new Character("VICTORYGREYMON","degimon_name_Dim027_016","dim027_mon14",3,1,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim027_mon15 = new Character("BLACKWARGREYMON","degimon_name_Dim027_007","dim027_mon15",3,2,3080, 3080, 3825.0f,800.0f);
    static public final Character dim027_mon16 = new Character("GAIOMON","degimon_name_Dim027_017","dim027_mon16",3,2,2200, 2200, 5100.0f,1440.0f);
    static public final Character dim027_mon17 = new Character("OMEGAMON ZWART","degimon_name_Dim027_008","dim027_mon17",3,1,3080, 3080, 5100.0f,1120.0f);
    //True Shadow Howl
    static public final Character dim028_mon14 = new Character("ZEEDGARURUMON","degimon_name_Dim028_016","dim028_mon14",3,3,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim028_mon15 = new Character("METALGARURUMON (BLACK)","degimon_name_Dim028_008","dim028_mon15",3,2,3080, 3080, 3825.0f,800.0f);
    static public final Character dim028_mon16 = new Character("MERUKIMON","degimon_name_Dim028_017","dim028_mon16",3,2,2640, 2640, 5100.0f,1280.0f);
    static public final Character dim028_mon17 = new Character("OMEGAMON ZWART","degimon_name_Dim028_004","dim028_mon17",3,1,3080, 3080, 5100.0f,1120.0f);
    //Dinosaur Roar
    static public final Character dim029_mon13 = new Character("WARGREYMON","degimon_name_Dim029_006","dim029_mon13",3,1,3080, 3080, 3825.0f,800.0f);
    static public final Character dim029_mon14 = new Character("VICTORYGREYMON","degimon_name_Dim029_017","dim029_mon14",3,1,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim029_mon15 = new Character("BLACKWARGREYMON","degimon_name_Dim029_011","dim029_mon15",3,2,3080, 3080, 3825.0f,800.0f);
    static public final Character dim029_mon16 = new Character("OMEGAMON","degimon_name_Dim029_007","dim029_mon16",3,1,3080, 3080, 5100.0f,1120.0f);
    static public final Character dim029_mon17 = new Character("OMEGAMON ZWART","degimon_name_Dim029_012","dim029_mon17",3,1,3080, 3080, 5100.0f,1120.0f);
    //Wolf Howl
    static public final Character dim030_mon13 = new Character("METALGARURUMON","degimon_name_Dim030_007","dim030_mon13",3,3,3080, 3080, 3825.0f,800.0f);
    static public final Character dim030_mon14 = new Character("ZEEDGARURUMON","degimon_name_Dim030_017","dim030_mon14",3,3,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim030_mon15 = new Character("METALGARURUMON (BLACK)","degimon_name_Dim030_012","dim030_mon15",3,2,3080, 3080, 3825.0f,800.0f);
    static public final Character dim030_mon16 = new Character("OMEGAMON","degimon_name_Dim030_001","dim030_mon16",3,1,3080, 3080, 5100.0f,1120.0f);
    static public final Character dim030_mon17 = new Character("OMEGAMON ZWART","degimon_name_Dim030_008","dim030_mon17",3,1,3080, 3080, 5100.0f,1120.0f);
    //Medarot
    static public final Character dim031_mon12 = new Character("OMEGAKNIGHT","degimon_name_Dim031_012","dim031_mon12",3,4,3300, 3300, 5100.0f,1120.0f);
    static public final Character dim031_mon13 = new Character("OMEDAMON","degimon_name_Dim031_013","dim031_mon13",3,1,3080, 3080, 4250.0f,960.0f);
    //Renamon EX
    static public final Character dim032_mon14 = new Character("SAKUYAMON","degimon_name_Dim032_003","dim032_mon14",3,3,3080, 3080, 4250.0f,1280.0f);
    static public final Character dim032_mon15 = new Character("PARASIMON","degimon_name_Dim032_012","dim032_mon15",3,2,2200, 2200, 4250.0f,1120.0f);
    static public final Character dim032_mon16 = new Character("KUZUHAMON: MAID MODE","degimon_name_Dim032_013","dim032_mon16",3,3,2200, 2200, 5100.0f,1280.0f);
    static public final Character dim032_mon17 = new Character("SAKUYAMON (X-ANTIBODY)","degimon_name_Dim032_014","dim032_mon17",3,3,3080, 3080, 5100.0f,1280.0f);
    //Louwemon EX
    static public final Character dim033_mon15 = new Character("GHOULMON","degimon_name_Dim033_016","dim033_mon15",3,3,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim033_mon16 = new Character("DINOTIGERMON","degimon_name_Dim033_017","dim033_mon16",3,3,2640, 2640, 3825.0f,960.0f);
    static public final Character dim033_mon17 = new Character("ANCIENTSPHINXMON","degimon_name_Dim033_001","dim033_mon17",3,2,2640, 2640, 5950.0f,1280.0f);
    //Ryudamon
    static public final Character dim034_mon14 = new Character("DURANDAMON","degimon_name_Dim034_014","dim034_mon14",3,1,2200, 2200, 4250.0f,1280.0f);
    static public final Character dim034_mon15 = new Character("OURYUMON","degimon_name_Dim034_015","dim034_mon15",3,1,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim034_mon16 = new Character("ZANBAMON","degimon_name_Dim034_016","dim034_mon16",3,2,2200, 2200, 3825.0f,1280.0f);
    static public final Character dim034_mon17 = new Character("RAGNALOARDMON","degimon_name_Dim034_017","dim034_mon17",3,2,3080, 3080, 5100.0f,1440.0f);
    //Espimon
    static public final Character dim035_mon14 = new Character("BRYWELUDRAMON","degimon_name_Dim035_015","dim035_mon14",3,3,3300, 3300, 4250.0f,960.0f);
    static public final Character dim035_mon15 = new Character("GHOULMON (BLACK)","degimon_name_Dim035_016","dim035_mon15",3,3,2640, 2640, 4250.0f,1120.0f);
    static public final Character dim035_mon16 = new Character("DEVITAMAMON","degimon_name_Dim035_017","dim035_mon16",3,3,2200, 2200, 3825.0f,1280.0f);
    static public final Character dim035_mon17 = new Character("RAGNALOARDMON","degimon_name_Dim035_002","dim035_mon17",3,2,3080, 3080, 5100.0f,1440.0f);
    //Wolfmon EX
    static public final Character dim036_mon14 = new Character("MAGNAGARURUMON","degimon_name_Dim036_016","dim036_mon14",3,4,2200, 2200, 4250.0f,1280.0f);
    static public final Character dim036_mon15 = new Character("ANCIENTGARURUMON","degimon_name_Dim036_017","dim036_mon15",3,3,3080, 3080, 4250.0f,1120.0f);
    static public final Character dim036_mon16 = new Character("ANCIENTMERMAIMON","degimon_name_Dim036_002","dim036_mon16",3,3,3300, 3300, 4675.0f,960.0f);
    static public final Character dim036_mon17 = new Character("SUSANOMON","degimon_name_Dim036_003","dim036_mon17",3,1,2640, 2640, 5525.0f,1920.0f);
    //Blitzmon EX
    static public final Character dim037_mon14 = new Character("ANCIENTBEETLEMON","degimon_name_Dim037_014","dim037_mon14",3,1,3080, 3080, 4250.0f,960.0f);
    static public final Character dim037_mon15 = new Character("GRANDISKUWAGAMON","degimon_name_Dim037_015","dim037_mon15",3,2,2640, 2640, 3825.0f,1280.0f);
    static public final Character dim037_mon16 = new Character("ANCIENTWISEMON","degimon_name_Dim037_016","dim037_mon16",3,2,2200, 2200, 4250.0f,1280.0f);
    static public final Character dim037_mon17 = new Character("MURMUKUSMON","degimon_name_Dim037_017","dim037_mon17",3,2,3080, 3080, 4250.0f,1120.0f);

    //BEM CHARACTERS
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    //25th Anniversary Characters
    static public final Character dim124_mon15 = new Character("BLITZGREYMON","degimon_name_dim124_mon15","dim124_mon15",3,2,5300, 5300, 5900.0f,2000.0f);
    static public final Character dim124_mon16 = new Character("BANCHOMAMEMON","degimon_name_dim124_mon16","dim124_mon16",3,3,4900, 4900, 5800.0f,1900.0f);
    static public final Character dim124_mon17 = new Character("SHINMONZAEMON","degimon_name_dim124_mon17","dim124_mon17",3,1,5100, 5100, 6500.0f,1950.0f);
    static public final Character dim124_mon23 = new Character("KAZUCHIMON","degimon_name_dim124_mon23","dim124_mon23",3,1,5000, 5000, 6500.0f,1950.0f);
    static public final Character dim125_mon14 = new Character("SIRIUSMON","degimon_name_dim125_mon14","dim125_mon14",3,1,5300, 5300, 6200.0f,1900.0f);
    //Gammamon
    static public final Character dim125_mon15 = new Character("CANNONDRAMON","degimon_name_dim125_mon15","dim125_mon15",3,3,5700, 5700, 6500.0f,1800.0f);
    static public final Character dim125_mon16 = new Character("SPINOMON","degimon_name_dim125_mon16","dim125_mon16",3,2,4900, 4900, 6450.0f,1850.0f);
    static public final Character dim125_mon17 = new Character("ARCTURUSMON","degimon_name_dim125_mon17","dim125_mon17",3,2,5200, 5200, 6200.0f,2000.0f);
    static public final Character dim125_mon22 = new Character("KUZUHAMON","degimon_name_dim125_mon22","dim125_mon22",3,3,5300, 5300, 6300.0f,1900.0f);
    static public final Character dim125_mon23 = new Character("PROXIMAMON","degimon_name_dim125_mon23","dim125_mon23",3,2,4900, 4900, 6500.0f,2000.0f);
    //Imperialdramon
    static public final Character dim128_mon06 = new Character("IMPERIALDRAMON: DRAGON MODE","degimon_name_dim128_mon06","dim128_mon06",3,4,4800, 4800, 6800.0f,1950.0f);
    static public final Character dim128_mon12 = new Character("IMPERIALDRAMON: FIGHTER MODE","degimon_name_dim128_mon12","dim128_mon12",3,4,5800, 5800, 6000.0f,2000.0f);
    static public final Character dim128_mon13 = new Character("IMPERIALDRAMON: PALADIN MODE","degimon_name_dim128_mon13","dim128_mon13",3,1,5500, 5500, 6400.0f,2200.0f);
    //Angoramon
    static public final Character dim126_mon14 = new Character("DIARBBITMON","degimon_name_dim126_mon14","dim126_mon14",3,1,5300, 5300, 6300.0f,2100.0f);
    static public final Character dim126_mon15 = new Character("MARSMON","degimon_name_dim126_mon15","dim126_mon15",3,1,5200, 5200, 6250.0f,1900.0f);
    static public final Character dim126_mon16 = new Character("PLUTOMON","degimon_name_dim126_mon16","dim126_mon16",3,2,5000, 5000, 6200.0f,2000.0f);
    static public final Character dim126_mon17 = new Character("GRANDRACMON","degimon_name_dim126_mon17","dim126_mon17",3,2,6000, 6000, 6400.0f,2000.0f);
    static public final Character dim126_mon23 = new Character("PIEDMON","degimon_name_dim126_mon23","dim126_mon23",3,2,5000, 5000, 6700.0f,1950.0f);
    //Jellymon
    static public final Character dim127_mon14 = new Character("VIKEMON","degimon_name_dim127_mon14","dim127_mon14",3,4,6000, 6000, 5600.0f,2100.0f);
    static public final Character dim127_mon15 = new Character("AMPHIMON","degimon_name_dim127_mon15","dim127_mon15",3,3,5200, 5200, 6500.0f,2100.0f);
    static public final Character dim127_mon16 = new Character("GIGASEADRAMON","degimon_name_dim127_mon16","dim127_mon16",3,3,5700, 5700, 6200.0f,2000.0f);
    static public final Character dim127_mon17 = new Character("PLATINUMNUMEMON","degimon_name_dim127_mon17","dim127_mon17",3,2,4900, 4900, 6300.0f,1800.0f);
    static public final Character dim127_mon23 = new Character("CTHYLLAMON","degimon_name_dim127_mon23","dim127_mon23",3,2,5000, 5000, 6500.0f,2100.0f);
    //Draconic Blaze
    static public final Character dim129_mon14 = new Character("SHINEGREYMON","degimon_name_dim129_mon14","dim129_mon14",3,1,6000, 6000, 4500.0f,2000.0f);
    static public final Character dim129_mon15 = new Character("SLAYERDRAMON","degimon_name_dim129_mon15","dim129_mon15",3,1,4800, 4800, 6300.0f,1950.0f);
    static public final Character dim129_mon16 = new Character("JESMON","degimon_name_dim129_mon16","dim129_mon16",3,3,5500, 5500, 6000.0f,2000.0f);
    static public final Character dim129_mon17 = new Character("BREAKDRAMON","degimon_name_dim129_mon17","dim129_mon17",3,2,6000, 6000, 4000.0f,1980.0f);
    static public final Character dim129_mon18 = new Character("EXAMON","degimon_name_dim129_mon18","dim129_mon18",3,3,5900, 5900, 6700.0f,2100.0f);
    static public final Character dim129_mon19 = new Character("SHINEGREYMON: BURST MODE","degimon_name_dim129_mon19","dim129_mon19",3,1,6100, 6100, 6000.0f,2100.0f);
    static public final Character dim129_mon23 = new Character("MAGNAKIDMON","degimon_name_dim129_mon23","dim129_mon23",3,2,5800, 5800, 6300.0f,2000.0f);
    //Rampage of the Beast
    static public final Character dim130_mon14 = new Character("MIRAGEGAOGAMON","degimon_name_dim130_mon14","dim130_mon14",3,3,4150, 4150, 6000.0f,2050.0f);
    static public final Character dim130_mon15 = new Character("APOLLOMON","degimon_name_dim130_mon15","dim130_mon15",3,1,6000, 6000, 5800.0f,2100.0f);
    static public final Character dim130_mon16 = new Character("DIANAMON","degimon_name_dim130_mon16","dim130_mon16",3,3,5800, 5800, 6400.0f,1800.0f);
    static public final Character dim130_mon17 = new Character("HEAVYLEOMON","degimon_name_dim130_mon17","dim130_mon17",3,1,6100, 6100, 3200.0f,2050.0f);
    static public final Character dim130_mon18 = new Character("GRACENOVAMON","degimon_name_dim130_mon18","dim130_mon18",3,1,6500, 6500, 5000.0f,2100.0f);
    static public final Character dim130_mon19 = new Character("MIRAGEGAOGAMON: BURST MODE","degimon_name_dim130_mon19","dim130_mon19",3,3,5800, 5800, 6800.0f,2100.0f);
    static public final Character dim130_mon23 = new Character("BELPHEMON: RAGE MODE","degimon_name_dim130_mon23","dim130_mon23",3,2,6000, 6000, 5600.0f,2100.0f);
    //Loogamon
    static public final Character dim131_mon14 = new Character("FENRILOOGAMON","degimon_name_dim131_mon14","dim131_mon14",3,2,5500, 5500, 6800.0f,2100.0f);
    static public final Character dim131_mon15 = new Character("RASENMON","degimon_name_dim131_mon15","dim131_mon15",3,3,6000, 6000, 6000.0f,1900.0f);
    static public final Character dim131_mon16 = new Character("RASENMON: FURY MODE","degimon_name_dim131_mon16","dim131_mon16",3,3,5800, 5800, 5800.0f,2000.0f);
    static public final Character dim131_mon17 = new Character("HIANDROMON","degimon_name_dim131_mon17","dim131_mon17",3,1,5200, 5200, 5100.0f,1850.0f);
    static public final Character dim131_mon18 = new Character("RUSTTYRANNOMON","degimon_name_dim131_mon18","dim131_mon18",3,2,5500, 5500, 5000.0f,1900.0f);
    static public final Character dim131_mon19 = new Character("FENRILOOGAMON: TAKEMIKAZUCHI","degimon_name_dim131_mon19","dim131_mon19",3,2,6100, 6100, 7000.0f,2150.0f);
    static public final Character dim131_mon23 = new Character("GALACTICMON","degimon_name_dim131_mon23","dim131_mon23",3,4,6000, 6000, 5600.0f,2100.0f);
    //Holy Wing
    static public final Character dim132_mon14 = new Character("PHOENIXMON","degimon_name_dim132_mon14","dim132_mon14",3,1,5900, 5900, 6100.0f,1900.0f);
    static public final Character dim132_mon15 = new Character("DOMINIMON","degimon_name_dim132_mon15","dim132_mon15",3,1,5700, 5700, 6350.0f,1900.0f);
    static public final Character dim132_mon16 = new Character("RAVEMON","degimon_name_dim132_mon16","dim132_mon16",3,1,5500, 5500, 5800.0f,1950.0f);
    static public final Character dim132_mon17 = new Character("XIANGPENGMON","degimon_name_dim132_mon17","dim132_mon17",3,1,6000, 6000, 5300.0f,1850.0f);
    static public final Character dim132_mon18 = new Character("SHAKAMON","degimon_name_dim132_mon18","dim132_mon18",3,1,6150, 6150, 6700.0f,2100.0f);
    static public final Character dim132_mon19 = new Character("RAVEMON: BURST MODE","degimon_name_dim132_mon19","dim132_mon19",3,1,5700, 5700, 6800.0f,2000.0f);
    static public final Character dim132_mon23 = new Character("METALLICDRAMON","degimon_name_dim132_mon23","dim132_mon23",3,3,5800, 5800, 6200.0f,2000.0f);
    //Forest Guardian
    static public final Character dim133_mon14 = new Character("HERCULESKABUTERIMON","degimon_name_dim133_mon14","dim133_mon14",3,1,5900, 5900, 5700.0f,2050.0f);
    static public final Character dim133_mon15 = new Character("ROSEMON","degimon_name_dim133_mon15","dim133_mon15",3,3,5600, 5600, 6200.0f,2000.0f);
    static public final Character dim133_mon16 = new Character("TIGERVESPAMON","degimon_name_dim133_mon16","dim133_mon16",3,2,5700, 5700, 6000.0f,1900.0f);
    static public final Character dim133_mon17 = new Character("GRANKUWAGAMON","degimon_name_dim133_mon17","dim133_mon17",3,4,5700, 5700, 5400.0f,1850.0f);
    static public final Character dim133_mon18 = new Character("TYRANTKABUTERIMON","degimon_name_dim133_mon18","dim133_mon18",3,2,6400, 6400, 6800.0f,2000.0f);
    static public final Character dim133_mon19 = new Character("ROSEMON: BURST MODE","degimon_name_dim133_mon19","dim133_mon19",3,3,5600, 5600, 6900.0f,1950.0f);
    static public final Character dim133_mon23 = new Character("BANCHOLILLYMON","degimon_name_dim133_mon23","dim133_mon23",3,3,5800, 5800, 6300.0f,2050.0f);

    //BOSS WAVE - 1
    static public final Character dim999_mon01 = new Character("OMEGAMON","degimon_name_Dim999_001","dim999_mon01",3,1,3000, 3000, 9000.0f,1400.0f);
    static public final Character dim999_mon02 = new Character("GALLANTMON","degimon_name_Dim999_002","dim999_mon02",3,2,9999, 9999, 7200.0f,3800.0f);

    //Ryudamon
    static public final Character dim136_mon14 = new Character("OURYUMON","degimon_name_dim136_mon14","dim136_mon14",3,1,5800, 5800, 6600.0f,1900.0f);
    static public final Character dim136_mon15 = new Character("BRIGADRAMON","degimon_name_dim136_mon15","dim136_mon15",3,2,5400, 5400, 6100.0f,2000.0f);
    static public final Character dim136_mon16 = new Character("NIDHOGGMON","degimon_name_dim136_mon16","dim136_mon16",3,2,6000, 6000, 5000.0f,1950.0f);
    static public final Character dim136_mon17 = new Character("SHINMONZAEMON","degimon_name_dim136_mon17","dim136_mon17",3,1,5500, 5500, 5200.0f,1900.0f);
    static public final Character dim136_mon18 = new Character("KINGETEMON","degimon_name_dim136_mon18","dim136_mon18",3,2,5000, 5000, 5500.0f,1800.0f);
    static public final Character dim136_mon19 = new Character("METALETEMON","degimon_name_dim136_mon19","dim136_mon19",3,2,5300, 5300, 5100.0f,1850.0f);
    static public final Character dim136_mon22 = new Character("DARKDRAMON","degimon_name_dim136_mon22","dim136_mon22",3,2,5900, 5900, 6100.0f,2000.0f);
    static public final Character dim136_mon23 = new Character("ALPHAMON: OURYUKEN","degimon_name_dim136_mon23","dim136_mon23",3,1,6100, 6100, 6600.0f,2150.0f);
    //Dorumon
    static public final Character dim137_mon14 = new Character("DORUGORAMON","degimon_name_dim137_mon14","dim137_mon14",3,3,5900, 5900, 6100.0f,1950.0f);
    static public final Character dim137_mon15 = new Character("DEXDORUGORAMON","degimon_name_dim137_mon15","dim137_mon15",3,2,5500, 5500, 5900.0f,2050.0f);
    static public final Character dim137_mon16 = new Character("CHAOSDRAMON","degimon_name_dim137_mon16","dim137_mon16",3,2,6100, 6100, 4900.0f,2000.0f);
    static public final Character dim137_mon17 = new Character("METALSEADRAMON","degimon_name_dim137_mon17","dim137_mon17",3,3,5400, 5400, 5000.0f,1800.0f);
    static public final Character dim137_mon18 = new Character("MACHINEDRAMON","degimon_name_dim137_mon18","dim137_mon18",3,2,5450, 5450, 4800.0f,1850.0f);
    static public final Character dim137_mon19 = new Character("GUNDRAMON","degimon_name_dim137_mon19","dim137_mon19",3,2,5000, 5000, 5200.0f,1800.0f);
    static public final Character dim137_mon22 = new Character("ALPHAMON","degimon_name_dim137_mon22","dim137_mon22",3,1,5700, 5700, 6300.0f,2100.0f);
    static public final Character dim137_mon23 = new Character("ALPHAMON: OURYUKEN","degimon_name_dim137_mon23","dim137_mon23",3,1,6100, 6100, 6600.0f,2150.0f);
    //D-3 White and Yellow
    static public final Character dim134_mon17 = new Character("VIKEMON","degimon_name_dim134_mon17","dim134_mon17",3,4,6000, 6000, 5600.0f,2100.0f);
    static public final Character dim134_mon18 = new Character("SERAPHIMON","degimon_name_dim134_mon18","dim134_mon18",3,1,5300, 5300, 6400.0f,1950.0f);
    static public final Character dim134_mon19 = new Character("IMPERIALDRAMON: DRAGON MODE","degimon_name_dim134_mon19","dim134_mon19",3,4,4800, 4800, 6800.0f,1950.0f);
    static public final Character dim134_mon20 = new Character("MAGNAMON","degimon_name_dim134_mon20","dim134_mon20",3,4,4700, 4700, 6300.0f,2000.0f);
    static public final Character dim134_mon22 = new Character("MALOMYOTISMON","degimon_name_dim134_mon22","dim134_mon22",3,2,5400, 5400, 6700.0f,2100.0f);
    static public final Character dim134_mon23 = new Character("BLACKWARGREYMON","degimon_name_dim134_mon23","dim134_mon23",3,2,5300, 5300, 5800.0f,2000.0f);
    //D-3 White and Red
    static public final Character dim135_mon15 = new Character("VALKYRIMON","degimon_name_dim135_mon15","dim135_mon15",3,4,5900, 5900, 6000.0f,2050.0f);
    static public final Character dim135_mon16 = new Character("MAGNADRAMON","degimon_name_dim135_mon16","dim135_mon16",3,1,6000, 6000, 6400.0f,2000.0f);
    static public final Character dim135_mon17 = new Character("IMPERIALDRAMON: FIGHTER MODE","degimon_name_dim135_mon17","dim135_mon17",3,4,5800, 5800, 6000.0f,2000.0f);
    static public final Character dim135_mon18 = new Character("IMPERIALDRAMON: PALADIN MODE","degimon_name_dim135_mon18","dim135_mon18",3,1,5500, 5500, 6400.0f,2200.0f);
    static public final Character dim135_mon22 = new Character("ARMAGEDDEMON","degimon_name_dim135_mon22","dim135_mon22",3,4,5800, 5800, 6700.0f,2150.0f);
    static public final Character dim135_mon23 = new Character("BIGUKKOMON","degimon_name_dim135_mon23","dim135_mon23",3,4,6100, 6100, 6000.0f,2200.0f);

    //BOSS WAVE - 2
    static public final Character dim999_mon03 = new Character("BLACKWARGREYMON","degimon_name_Dim999_003","dim999_mon03",3,2,9999, 9999, 7200.0f,3800.0f);
    static public final Character dim999_mon04 = new Character("MILLENNIUMMON","degimon_name_Dim999_004","dim999_mon04",3,2,9999, 9999, 7200.0f,3800.0f);
    static public final Character dim999_mon05 = new Character("BIGUKKOMON","degimon_name_Dim999_005","dim999_mon05",3,4,9999, 9999, 7200.0f,3800.0f);
    static public final Character dim999_mon06 = new Character("CHERUBIMON (BLACK)","degimon_name_Dim999_006","dim999_mon06",3,1,9999, 9999, 7200.0f,3800.0f);
    
    //Pulsemon
    static public final Character dim138_mon15 = new Character("MITAMAMON","degimon_name_dim138_mon15","dim138_mon15",3,1,5700, 5700, 5600.0f,1900.0f);
    static public final Character dim138_mon16 = new Character("RAIJINMON","degimon_name_dim138_mon16","dim138_mon16",3,2,5200, 5200, 6000.0f,1850.0f);
    static public final Character dim138_mon17 = new Character("RAIDENMON","degimon_name_dim138_mon17","dim138_mon17",3,2,5600, 5600, 4900.0f,1800.0f);
    static public final Character dim138_mon18 = new Character("KAZUCHIMON","degimon_name_dim138_mon18","dim138_mon18",3,1,5000, 5000, 6500.0f,1950.0f);
    static public final Character dim138_mon19 = new Character("FENRILOOGAMON: TAKEMIKAZUCHI","degimon_name_dim138_mon19","dim138_mon19",3,2,6100, 6100, 7000.0f,2150.0f);
    static public final Character dim138_mon22 = new Character("SHOUTMON DX","degimon_name_dim138_mon22","dim138_mon22",3,3,5800, 5800, 6000.0f,2000.0f);
    static public final Character dim138_mon23 = new Character("SHOUTMON X7","degimon_name_dim138_mon23","dim138_mon23",3,3,6200, 6200, 5800.0f,2100.0f);

    //BOSS WAVE - 3
    static public final Character dim999_mon07 = new Character("OMEGAMON ZWART DEFEAT","degimon_name_Dim999_007","dim999_mon07",3,2,6200, 6200, 5800.0f,2100.0f);

    
    static public ArrayList<Character> megaArray = new ArrayList<Character>();
}
