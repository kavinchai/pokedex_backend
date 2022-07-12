package com.bushelpowered.pokedex.dto.response

import com.bushelpowered.pokedex.model.Pokemon

data class LoginTrainerResponse(
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val capturedPokemon: List<Pokemon>
)
