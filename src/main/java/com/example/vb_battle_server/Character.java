package com.example.vb_battle_server;

public class Character {
    public String name;
    public String namekey;
    public String charaId;
    public int stage;
    public int attribute;
    public int baseHp;
    public int currentHp;
    public float baseBp;
    public float baseAp;

    public Character(String name, String namekey, String charaId, int stage, int attribute, int baseHp, int currentHp, float baseBp, float baseAp) {    
        this.name = name;
        this.namekey = namekey;
        this.charaId = charaId;
        this.stage = stage;
        this.attribute = attribute;
        this.baseHp = baseHp;
        this.currentHp = currentHp;
        this.baseBp = baseBp;
        this.baseAp = baseAp;
    }
} 