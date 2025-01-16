package com.example.vb_battle_server

class Character (
    /*
    val dimId: Long,
    val monIndex: Int,
     */
    var name: String,
    var stage: Int, // These should be replaced with enums
    var attribute: Int, // This one too
    var baseHp: Int,
    var currentHp: Int,
    var baseBp: Float,
    var baseAp: Float,
    /*
    val sprite1: ByteArray,
    val sprite2: ByteArray,
    val nameWidth: Int,
    val nameHeight: Int,
    val spritesWidth: Int,
    val spritesHeight: Int
    */
)