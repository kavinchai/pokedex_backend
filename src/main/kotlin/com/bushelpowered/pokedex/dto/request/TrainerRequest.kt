package com.bushelpowered.pokedex.dto.request

data class TrainerRequest(
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String
)
