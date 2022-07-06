package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.PokemonResponse
import com.bushelpowered.pokedex.dto.TrainerResponse
import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.entity.Trainer

fun Pokemon.toPokemonResponse(): PokemonResponse {
    val typeResponseList = mutableListOf<String>()
    val abilityResponseList = mutableListOf<String>()
    val eggGroupResponseList = mutableListOf<String>()
    this.type.forEach {
        typeResponseList.add(it.type)
    }
    this.ability.forEach {
        abilityResponseList.add(it.ability)
    }
    this.eggGroup.forEach {
        eggGroupResponseList.add(it.eggGroup)
    }
    return PokemonResponse(
        id = this.id,
        name = this.name,
        type = typeResponseList,
        height = this.height,
        weight = this.weight,
        ability = abilityResponseList,
        eggGroup = eggGroupResponseList,
        stats = this.stats,
        genus = this.genus[0].genus,
        description = this.description
    )
}

fun Trainer.toTrainerResponse(): TrainerResponse {
    val pokemonResponse = mutableListOf<String>()
    this.capturedPokemon.forEach{
        pokemonResponse.add(it.name)
    }
    return TrainerResponse(
        id = this.id,
        username = this.username,
        firstname = this.firstname,
        lastname = this.lastname,
        email = this.email,
        capturedPokemon = pokemonResponse
    )
}