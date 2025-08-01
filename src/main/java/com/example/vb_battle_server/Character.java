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

    // Getter methods
    public String getName() { return name; }
    public String getNamekey() { return namekey; }
    public String getCharaId() { return charaId; }
    public int getStage() { return stage; }
    public int getAttribute() { return attribute; }
    public int getBaseHp() { return baseHp; }
    public int getCurrentHp() { return currentHp; }
    public float getBaseBp() { return baseBp; }
    public float getBaseAp() { return baseAp; }

    // Setter methods
    public void setName(String name) { this.name = name; }
    public void setNamekey(String namekey) { this.namekey = namekey; }
    public void setCharaId(String charaId) { this.charaId = charaId; }
    public void setStage(int stage) { this.stage = stage; }
    public void setAttribute(int attribute) { this.attribute = attribute; }
    public void setBaseHp(int baseHp) { this.baseHp = baseHp; }
    public void setCurrentHp(int currentHp) { this.currentHp = currentHp; }
    public void setBaseBp(float baseBp) { this.baseBp = baseBp; }
    public void setBaseAp(float baseAp) { this.baseAp = baseAp; }
} 