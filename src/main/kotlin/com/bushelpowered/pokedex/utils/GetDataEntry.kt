package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.TypeRepository

fun getPokeIdFromString(
    pokemonName: String,
    pokeRepo: PokemonRepository
): Int {
    pokeRepo.findAll().forEach { pokemon ->
        if (pokemon.name.lowercase() == pokemonName.lowercase()) {
            return pokemon.id
        }
    }
    return 0
}

fun getTypeIdFromString(
    typeString: String,
    typeRepo: TypeRepository
): Int {
    typeRepo.findAll().forEach { typeEntity ->
        if (typeEntity.type.lowercase() == typeString.lowercase()) {
            return typeEntity.id
        }
    }
    return 0
}

