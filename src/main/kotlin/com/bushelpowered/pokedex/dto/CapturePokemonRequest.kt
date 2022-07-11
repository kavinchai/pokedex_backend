package com.bushelpowered.pokedex.dto

import org.valiktor.functions.isPositive
import org.valiktor.validate

data class CapturePokemonRequest(
    val trainerId: Int,
    val pokemonId: Int
)