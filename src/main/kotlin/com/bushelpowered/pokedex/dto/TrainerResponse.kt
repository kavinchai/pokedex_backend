package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entity.Pokemon

data class TrainerResponse(
    val trainerId: Int,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val emailId: String, // why do we have an emailId? Is this just the user's email?
    val capturedPokemon: List<Pokemon>? // this should not be nullable, and we want this to be List<PokemonResponse>
)
