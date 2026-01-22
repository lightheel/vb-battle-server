package com.example.restservice;

public record PVP(String status, int state, int currentRound, int playerHP, int opponentHP, int playerMaxHP, int opponentMaxHP, boolean playerAttackHit, int playerAttackDamage, int opponentAttackDamage, String winner) { }