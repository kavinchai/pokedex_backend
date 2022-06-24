package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entity.Pokemon

data class TrainerResponse(
    val trainerId: Int,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val emailId: String,
    val capturedPokemon: List<Pokemon>?
)
