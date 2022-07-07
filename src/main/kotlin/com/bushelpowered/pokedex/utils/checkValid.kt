package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.TypeRepository

fun checkValidPokemonList(
    pokemonList: List<Int>,
    pokemonRepository: PokemonRepository
): Boolean {
    pokemonList.forEach { pokemonId ->
        if (!pokemonRepository.existsById(pokemonId)) {
            return false
        }
    }
    return true
}

fun checkValidPokemon(
    pokemonName: String,
    pokemonRepo: PokemonRepository
): Boolean {
    pokemonRepo.findAll().forEach{
        if (it.name.lowercase() == pokemonName.lowercase()){
            return true
        }
    }
    return false
}

fun checkValidType(
    type: String,
    typeRepo: TypeRepository
): Boolean {
    typeRepo.findAll().forEach{
        if (it.type.lowercase() == type.lowercase()){
            return true
        }
    }
    return false
}

