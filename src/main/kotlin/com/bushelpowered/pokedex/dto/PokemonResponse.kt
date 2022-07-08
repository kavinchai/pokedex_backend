package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entity.PokemonStat

data class PokemonResponse(
    val id: Int,
    val name: String,
    val type: List<String>,
    val height: Double,
    val weight: Double,
    val ability: List<String>,
    val eggGroup: List<String>,
    val stats: PokemonStatResponse,
    val genus: String?,
    val description: String
)
