package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.CapturePokemonResponse
import com.bushelpowered.pokedex.dto.CaughtPokemonResponse
import com.bushelpowered.pokedex.entity.CapturedPokemon

fun CapturedPokemon.toResponse(): CapturePokemonResponse{
    return CapturePokemonResponse(
        uniqueId = this.id,
        trainerId = this.trainer,
        capturedInfo = CaughtPokemonResponse(this.pokemon, this.timesCaught)
    )
}