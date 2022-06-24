package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entity.EggGroups
import com.bushelpowered.pokedex.entity.PokemonAbilities
import com.bushelpowered.pokedex.entity.PokemonStats
import com.bushelpowered.pokedex.entity.PokemonTypes

data class PokemonResponse(
    val pokemonId: Int,
    val name: String,
    val pokemonTypes: PokemonTypes,
    val height: Double,
    val weight: Double,
    val pokemonAbilities: PokemonAbilities,
    val eggGroups: EggGroups,
    val pokemonStats: PokemonStats,
    val genus: String,
    val description: String
)
