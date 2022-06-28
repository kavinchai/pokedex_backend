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
    val pokemonAbilities: PokemonAbilities, // this maps to the entity, we would ideally use a PokemonAbilitiesResponse as well
    val eggGroups: EggGroups, // this maps to the entity, we would ideally use an EggGroupsResponse as well
    val pokemonStats: PokemonStats,// this maps to the entity, we would ideally use a PokemonStatsResponse as well
    val genus: String,
    val description: String
)
