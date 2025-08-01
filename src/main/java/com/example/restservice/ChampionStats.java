/*
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
    //Champion
    static public final Character geoGreymon = new Character("Geo Greymon",1,3,3600, 3600, 3800,1300);
    static public final Character coredramon_blue = new Character("Coredramon - Blue",1,3,3300, 3300, 5800,1100);
    static public final Character baoHackmon = new Character("Bao Hackmon",1,2,3500, 3500, 5600,1300);
    static public final Character coredramon_green = new Character("Coredramon - Green",1,1,3600, 3600, 3500,1250);
    static public final Character arresterdramon = new Character("Arresterdramon",1,3,3600, 3600, 5700,1250);
    static public final Character gaogamon = new Character("Gaogamon",1,2,3400, 3400, 6500,1200);
    static public final Character firamon = new Character("Firamon",1,3,3700, 3700, 5000,1310);
    static public final Character lekismon = new Character("Lekismon",1,2,3600, 3600, 5500,1100);
    static public final Character leomon = new Character("Leomon",1,3,3300, 3300, 5000,1100);
    static public final Character madLeomon = new Character("Mad Leomon",1,1,3400, 3400, 5500,1300);
    static public final Character birdramon = new Character("Birdramon",1,3,3500, 3500, 5500,1200);
    static public final Character pidmon = new Character("Pidmon",1,3,3700, 3700, 5300,1250);
    static public final Character peckmon = new Character("Peckmon",1,3,3400, 3400, 5400,1150);
    static public final Character xiquemon = new Character("Xiquemon",1,3,3600, 3600, 5100,1200);
    static public final Character thunderbirmon = new Character("Thunderbirmon",1,2,3600, 3600, 5600,1250);
    static public final Character kabuterimon = new Character("Kabuterimon",1,3,3800, 3800, 5000,1250);
    static public final Character sunflowmon = new Character("Sunflowmon",1,2,3500, 3500, 5600,1300);
    static public final Character waspmon = new Character("Waspmon",1,1,3400, 3400, 5400,1200);
    static public final Character kuwagamon = new Character("Kuwagamon",1,1,3700, 3700, 5250,1200);
    static public final Character hudiemon = new Character("Hudiemon",1,4,3600, 3600, 5800,1200);
    static public final Character loogarmon = new Character("Loogarmon",1,1,3300, 3300, 5400,1300);
    static public final Character filmon = new Character("Filmon",1,2,3600, 3600, 5200,1200);
    static public final Character tyrannomon = new Character("Tyrannomon",1,2,3700, 3700, 5000,1200);
    static public final Character damemon = new Character("Damemon",1,1,3300, 3300, 4800,1150);
    static public final Character snatchmon = new Character("Snatchmon",1,4,3400, 3400, 5300,1250);
    static public final Character ginryumon = new Character("Ginryumon",1,3,3600, 3600, 5300,1150);
    static public final Character hicommandramon = new Character("Hi-Commandramon",1,1,4000, 4000, 5000,1250);
    static public final Character deltamon = new Character("Hudiemon",1,1,3600, 3600, 4800,1300);
    static public final Character numemon = new Character("Numemon",1,1,3500, 3500, 4600,1100);
    static public final Character sealsdramon = new Character("Sealsdramon",1,1,3500, 3500, 5300,1250);
    static public final Character dorugamon = new Character("Dorugamon",1,2,3500, 3500, 5200,1200);
    static public final Character deathXDorugamon = new Character("Death-X-Dorugamon",1,1,3200, 3200, 4900,1300);
    static public final Character darkTyrannomon = new Character("Dark Tyrannomon",1,1,3300, 3300, 4700,1250);
    static public final Character airdramon = new Character("Airdramon",1,3,3200, 3200, 4900,1200);
    static public final Character raptordramon = new Character("Raptordramon",1,3,3400, 3400, 5700,1200);
    static public final Character bulkmon = new Character("Bulkmon",1,3,3600, 3600, 5200,1300);
    static public final Character reppamon = new Character("Reppamon",1,3,3700, 3700, 5000,1200);
    static public final Character thunderballmon = new Character("Thunderballmon",1,2,3300, 3300, 4800,1150);
    static public final Character bladeKuwagamon = new Character("Blade Kuwagamon",1,1,3000, 3000, 5000,1100);
    static public final Character shoutmonX4 = new Character("Shoutmon X4",1,2,4000, 4000, 5500,1350);
    static public final Character betelGammamon = new Character("Betel Gammamon",1,3,3600, 3600, 5200,1200);
    static public final Character kausGammamon = new Character("Kaus Gammamon",1,2,3400, 3400, 5100,1250);
    static public final Character wezenGammamon = new Character("Wezen Gammamon",1,2,3500, 3500, 4800,1300);
    static public final Character gulusGammamon = new Character("Gulus Gammamon",1,1,3700, 3700, 5300,1250);
    static public final Character symbareAngoramon = new Character("Symbare Angoramon",1,3,3500, 3500, 5500,1250);
    static public final Character siesamon = new Character("Siesamon",1,3,3200, 3200, 5100,1200);
    static public final Character dogmon = new Character("Dogmon",1,2,3700, 3700, 4800,1300);
    static public final Character sangloupmon = new Character("Sangloupmon",1,1,3000, 3000, 5800,1250);
    static public final Character publimon = new Character("Publimon",1,2,3100, 3100, 5200,1250);
    static public final Character teslaJellymon = new Character("Tesla Jellymon",1,2,3400, 3400, 5800,1200);
    static public final Character ikkakumon = new Character("Ikkakumon",1,3,3700, 3700, 5500,1250);
    static public final Character tobiumon = new Character("Tobiumon",1,3,3600, 3600, 5600,1200);
    static public final Character karatsukiNumemon = new Character("Karatsuki Numemon",1,1,3000, 3000, 4800,1150);
    static public final Character chamblemon = new Character("Chamblemon",1,1,3600, 3600, 5100,1200);
    static public final Character kinkakumon = new Character("Kinkakumon",1,1,3700, 3700, 4800,1300);
    static public final Character ankylomon = new Character("Ankylomon",1,4,4000, 4000, 4400,1350);
    static public final Character angemon = new Character("Angemon",1,3,3800, 3800, 5500,1300);
    static public final Character xvmon = new Character("XV-mon",1,4,3500, 3500, 5000,1300);
    static public final Character digmon = new Character("Digmon",1,4,3500, 3500, 4200,1250);
    static public final Character submarimon = new Character("Submarimon",1,4,3300, 3300, 5100,1200);
    static public final Character pegasmon = new Character("Pegasmon",1,4,3300, 3300, 5300,1100);
    static public final Character fladramon = new Character("Fladramon",1,4,3450, 3450, 4800,1200);
    static public final Character lighdramon = new Character("Lighdramon",1,4,3400, 34400, 5200,1100);
    static public final Character aquilamon = new Character("Aquilamon",1,4,3300, 3300, 5800,1200);
    static public final Character tailmon = new Character("Tailmon",1,3,3500, 3500, 5600,1150);
    static public final Character stingmon = new Character("Stingmon",1,4,3800, 3800, 5100,1200);
    static public final Character holsmon = new Character("Holsmon",1,4,3200, 3200, 5300,1100);
    static public final Character nefertimon = new Character("Nefertimon",1,4,3400, 3400, 5700,1100);
    static public final Character shurimon = new Character("Shurimon",1,4,3100, 3100, 5200,1150);
    static public final Character greymon = new Character("Greymon",1,3,3500, 3500, 5100,1300);
    static public ArrayList<Character> championArray = new ArrayList<Character>();
}
*/