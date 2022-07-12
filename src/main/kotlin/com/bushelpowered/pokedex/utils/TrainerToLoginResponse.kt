package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.response.LoginTrainerResponse
import com.bushelpowered.pokedex.model.Trainer

fun Trainer.toLoginResponse(): LoginTrainerResponse{
    val pokemonNameList =  this.capturedPokemon.map{it.name}
    return LoginTrainerResponse(
        id = this.id,
        username = this.username,
        firstname = this.firstname,
        lastname = this.lastname,
        email = this.email,
        capturedPokemon = pokemonNameList
    )
}