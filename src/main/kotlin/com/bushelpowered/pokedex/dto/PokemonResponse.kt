package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entity.PokemonStat

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Float,
    val weight: Float,
    val stats: PokemonStat,
    val description: String
)
