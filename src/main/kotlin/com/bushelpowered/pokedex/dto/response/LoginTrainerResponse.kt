package com.bushelpowered.pokedex.dto.response

data class LoginTrainerResponse(
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val capturedPokemon: List<String>
)
