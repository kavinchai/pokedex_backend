package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entity.EggGroup
import com.bushelpowered.pokedex.entity.PokemonStats

data class PokemonResponse(
    val id: Int,
    val name: String,
//    val pokemonTypes: PokemonTypes,
    val height: Double,
    val weight: Double,
//    val pokemonAbilities: Ability,
    val eggGroup: EggGroup,
    val pokemonStats: PokemonStats,
    val genus: String,
    val description: String
)
