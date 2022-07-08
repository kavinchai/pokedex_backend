package com.bushelpowered.pokedex.dto

data class PokemonStatResponse(
    val id: Int,
    val hp: Int,
    val speed: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int
)
