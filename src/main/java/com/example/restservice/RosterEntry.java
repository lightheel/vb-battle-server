package com.example.restservice;

import com.example.vb_battle_server.Character;

/**
 * One entry in a stage roster: the Digimon (with optional ownerUsername) and when it was added.
 */
public record RosterEntry(Character character, long addedAt) { }
