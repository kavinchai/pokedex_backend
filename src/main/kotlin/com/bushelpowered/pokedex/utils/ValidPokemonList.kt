package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.repository.PokemonRepository

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