package com.bushelpowered.pokedex.dto

import org.valiktor.functions.isPositive
import org.valiktor.validate

data class CapturePokemonRequest(
    val trainerId: Int,
    val pokemonId: Int
){
    init{
        validate(this){
            validate(CapturePokemonRequest::trainerId).isPositive()
            validate(CapturePokemonRequest::pokemonId).isPositive()
        }
    }
}