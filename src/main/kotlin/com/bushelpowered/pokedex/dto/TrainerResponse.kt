package com.bushelpowered.pokedex.dto

import com.bushelpowered.pokedex.entity.Pokemon

data class TrainerResponse(
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val capturedPokemon: List<Pokemon>?
)
