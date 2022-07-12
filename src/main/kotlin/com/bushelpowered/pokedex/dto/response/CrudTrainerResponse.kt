package com.bushelpowered.pokedex.dto.response

data class CrudTrainerResponse(
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String
)
