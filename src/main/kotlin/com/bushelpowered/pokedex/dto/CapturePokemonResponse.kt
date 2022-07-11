package com.bushelpowered.pokedex.dto

data class CapturePokemonResponse(
    val uniqueId: Int,
    val trainerId: Int,
    val capturedInfo: CaughtPokemonResponse
)
