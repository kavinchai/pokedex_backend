package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entity.Ability
import com.bushelpowered.pokedex.entity.PokemonStat
import com.bushelpowered.pokedex.entity.Type

data class PokemonResponse(
    val id: Int,
    val name: String,
    val type: List<TypeResponse>,
    val height: Double,
    val weight: Double,
    val ability: List<AbilityResponse>,
    val eggGroup: List<EggGroupResponse>,
    val stats: PokemonStat,
    val genus: List<GenusResponse>,
    val description: String
)
