package com.example.restservice;

import com.example.vb_battle_server.Character;

import java.util.ArrayList;

public class UltimateStats {

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
    //Ultimate
    static public final Character rizeGreymon = new Character("Rize Greymon",2,3,4600, 4600, 4000,1550);
    static public final Character wingdramon = new Character("Wingdramon",2,3,4000, 4000, 6200,1500);
    static public final Character saviorHackmon = new Character("Savior Hackmon",2,2,4600, 4600, 5800,1600);
    static public final Character groundramon = new Character("Groundramon",2,1,4600, 4600, 3800,1580);
    static public final Character arresterdramonSuperiorMode = new Character("Arresterdramon: Superior Mode",2,3,4400, 4400, 6100,1550);
    static public final Character machGaogamon = new Character("Mach Gaogamon",2,2,3100, 3100, 8000,1500);
    static public final Character flaremon = new Character("Flaremon",2,3,4400, 4400, 5300,1600);
    static public final Character crescemon = new Character("Crescemon",2,2,4400, 4400, 7500,1450);
    static public final Character grapLeomon = new Character("GrapLeomon",2,3,4300, 4300, 5700,1550);
    static public final Character astamon = new Character("Astamon",2,1,4100, 4100, 6200,1500);
    static public final Character garudamon = new Character("Garudamon",2,3,4200, 4200, 5900,1450);
    static public final Character arkhaiAngemon = new Character("Arkhai Angemon",2,3,4250, 4250, 6100,1400);
    static public final Character yatagaramon_2006 = new Character("Yatagaramon - 2006",2,3,3900, 3900, 6200,1400);
    static public final Character huankunmon = new Character("Huankunmon",2,3,4500, 4500, 5400,1550);
    static public final Character jazarichmon = new Character("Jazarichmon",2,2,4200, 4200, 6000,1600);
    static public final Character megaKabuterimon_red = new Character("MegaKabuterimon - Red",2,3,4700, 4700, 5300,1500);
    static public final Character lilamon = new Character("Lilamon",2,2,4300, 4300, 6000,1600);
    static public final Character cannonbeemon = new Character("Cannonbeemon",2,1,4500, 4500, 5400,1550);
    static public final Character okuwamon = new Character("Okuwamon",2,1,4500, 4500, 5100,1450);
    static public final Character eosmon_perfect = new Character("Eosmon - Perfect",2,4,4500, 4500, 6000,1500);
    static public final Character soloogarmon = new Character("Soloogarmon",2,1,4300, 4300, 6200,1550);
    static public final Character stiffilmon = new Character("Stiffilmon",2,2,4600, 4600, 5600,1500);
    static public final Character helloogarmon = new Character("Helloogarmon",2,1,4000, 4000, 4800,1600);
    static public final Character andromon = new Character("Andromon",2,3,3900, 3900, 5000,1450);
    static public final Character masterTyrannomon = new Character("MasterTyrannomon",2,3,4200, 4200, 4900,1500);
    static public final Character destromon = new Character("Destromon",2,4,4300, 4300, 5700,1500);
    static public final Character hisyaryumon = new Character("Hisyaryumon",2,3,4500, 4500, 6500,1500);
    static public final Character cargodramon = new Character("Cargodramon",2,1,5000, 5000, 5500,1400);
    static public final Character orochimon = new Character("Orochimon",2,1,4700, 4700, 4700,1450);
    static public final Character monzaemon = new Character("Monzaemon",2,3,4000, 4000, 5100,1400);
    static public final Character etemon = new Character("Etemon",2,1,4300, 4300, 4950,1300);
    static public final Character tankdramon = new Character("Tankdramon",2,1,4800, 4800, 5900,1600);
    static public final Character doruguremon = new Character("Doruguremon",2,2,5000, 5000, 6400,1400);
    static public final Character dexDorugreymon = new Character("DexDorugreymon",2,1,4400, 4400, 5400,1550);
    static public final Character metalTyrannomon = new Character("Metal Tyrannomon",2,1,4500, 4500, 4900,1400);
    static public final Character megadramon = new Character("Megadramon",2,1,4300, 4300, 4900,1350);
    static public final Character gigadramon = new Character("Gigadramon",2,1,4250, 4250, 4800,1250);
    static public final Character grademon = new Character("Grademon",2,3,4700, 4700, 6000,1650);
    static public final Character boutmon = new Character("Boutmon",2,3,4500, 4500, 5230,1600);
    static public final Character chirinmon = new Character("Chirinmon",2,3,4200, 4200, 5400,1500);
    static public final Character giromon = new Character("Giromon",2,3,4400, 4400, 5000,1450);
    static public final Character kyukimon = new Character("Kyukimon",2,1,3900, 3900, 5100,1450);
    static public final Character metallifeKuwagamon = new Character("Metallife Kuwagamon",2,1,4000, 4000, 4800,1400);
    static public final Character omegaShoutmon = new Character("Omega Shoutmon",2,2,4900, 4900, 5900,1700);
    static public final Character canoweissmon = new Character("Canoweissmon",2,3,4500, 4500, 5600,1550);
    static public final Character fumamon = new Character("Fumamon",2,2,4500, 4500, 5800,1450);
    static public final Character ghilliedhumon = new Character("Ghilliedhumon",2,2,3500, 3500, 6000,1610);
    static public final Character bombermon = new Character("Bombermon",2,1,4500, 4500, 5100,1600);
    static public final Character zanmetsumon = new Character("Zanmetsumon",2,1,4300, 4300, 5800,1550);
    static public final Character regulusmon = new Character("Regulusmon",2,1,4000, 4000, 5700,1610);
    static public final Character myotismon = new Character("Myotismon",2,1,4800, 4800, 5900,1450);
    static public final Character lamortmon = new Character("Lamortmon",2,3,4600, 4600, 5800,1550);
    static public final Character cerberumon = new Character("Cerberumon",2,3,4500, 4500, 5700,1500);
    static public final Character hippoGryphonmon = new Character("HippoGryphonmon",2,2,4200, 4200, 6000,1500);
    static public final Character digitamamon = new Character("Digitamamon",2,2,4200, 4200, 5300,1610);
    static public final Character matadormon = new Character("Matadormon",2,1,4600, 4600, 6200,1550);
    static public final Character mephistomon = new Character("Mephistomon",2,1,4200, 4200, 5800,1610);
    static public final Character feresmon = new Character("Feresmon",2,1,4200, 4200, 6000,1550);
    static public final Character oleamon = new Character("Oleamon",2,1,4800, 4800, 5550,1500);
    static public final Character bastemon = new Character("Bastemon",2,1,4600, 4600, 5800,1400);
    static public final Character thetismon = new Character("Thetismon",2,2,4200, 4200, 6000,1600);
    static public final Character zudomon = new Character("Zudomon",2,3,4500, 4500, 5300,1610);
    static public final Character waruSeadramon = new Character("WaruSeadramon",2,1,4300, 4300, 5300,1500);
    static public final Character marineChimairamon = new Character("Marine Chimairamon",2,3,4000, 4000, 5600,1610);
    static public final Character scorpiomonX = new Character("Scorpiomon X",2,2,4600, 4600, 6200,1500);
    static public final Character blackKingNumemon = new Character("Black King Numemon",2,1,3500, 3500, 5100,1400);
    static public final Character majiramon = new Character("Majiramon",2,2,4800, 4800, 5600,1500);
    static public final Character toropiamon = new Character("Toropiamon",2,1,4200, 4200, 5800,1610);
    static public final Character shakkoumon = new Character("Shakkoumon",2,4,5000, 5000, 5900,1580);
    static public final Character magnaAngemon = new Character("MagnaAngemon",2,3,4000, 4000, 6000,1400);
    static public final Character paildramon = new Character("Paildramon",2,4,4800, 4800, 5500,1450);
    static public final Character arachnemon = new Character("Arachnemon",2,1,4800, 4800, 5600,1400);
    static public final Character silphymon = new Character("Silphymon",2,4,4800, 4800, 6200,1500);
    static public final Character angewomon = new Character("Angewomon",2,3,3900, 3900, 6000,1350);
    static public final Character dinobeemon = new Character("Dinobeemon",2,4,4900, 4900, 5900,1300);
    static public final Character mummymon = new Character("Mummymon",2,1,4900, 4900, 5600,1450);
    static public final Character chimairamon = new Character("Chimairamon",2,2,5100, 5100, 6100,1700);
    static public final Character metalGreymon = new Character("Metal Greymon",2,1,5100, 5100, 5400,1500);
    static public final Character mamemon = new Character("Mamemon",2,2,4400, 4400, 5500,1540);
    static public ArrayList<Character> ultimateArray = new ArrayList<Character>();

}
