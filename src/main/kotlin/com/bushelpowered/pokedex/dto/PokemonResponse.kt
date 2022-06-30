package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entity.EggGroups
import com.bushelpowered.pokedex.entity.Ability
import com.bushelpowered.pokedex.entity.PokemonStats

data class PokemonResponse(
    val id: Int,
    val name: String,
//    val pokemonTypes: PokemonTypes,
    val height: Double,
    val weight: Double,
//    val pokemonAbilities: Ability,
    val eggGroups: EggGroups,
    val pokemonStats: PokemonStats,
    val genus: String,
    val description: String
)
