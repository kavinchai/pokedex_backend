package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.response.LoginTrainerResponse
import com.bushelpowered.pokedex.model.Trainer

fun Trainer.toLoginResponse(): LoginTrainerResponse {
    val pokemonNameList = this.capturedPokemon.map { it.name.replaceFirstChar{char -> char.uppercase()} }
    return LoginTrainerResponse(
        id = this.id,
        username = this.username,
        firstname = this.firstname.replaceFirstChar{char -> char.uppercase()},
        lastname = this.lastname.replaceFirstChar{char -> char.uppercase()},
        email = this.email,
        capturedPokemon = pokemonNameList
    )
}