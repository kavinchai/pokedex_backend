package com.bushelpowered.pokedex.dto.response

data class CapturePokemonResponse(
    val uniqueId: Int,
    val trainerId: Int,
    val capturedInfo: CaughtPokemonResponse
)
