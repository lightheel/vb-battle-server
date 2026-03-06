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
    /** Set for player-added roster entries; null for catalog / initial roster. */
    private String ownerUsername;

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
        this.ownerUsername = null;
    }

    /** Copy constructor; copies all fields including ownerUsername. */
    public Character(Character other) {
        this.name = other.name;
        this.namekey = other.namekey;
        this.charaId = other.charaId;
        this.stage = other.stage;
        this.attribute = other.attribute;
        this.baseHp = other.baseHp;
        this.currentHp = other.currentHp;
        this.baseBp = other.baseBp;
        this.baseAp = other.baseAp;
        this.ownerUsername = other.ownerUsername;
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
    public String getOwnerUsername() { return ownerUsername; }

    /** Display name for client: "Username's DigimonName" when owner is set, else just name. */
    public String getDisplayName() {
        if (ownerUsername != null && !ownerUsername.isEmpty()) {
            return ownerUsername + "'s " + name;
        }
        return name;
    }

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
    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }
} 