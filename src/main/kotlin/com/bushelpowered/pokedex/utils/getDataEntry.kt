package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.TypeRepository

fun getPokeIdFromString(
    name: String,
    pokeRepo: PokemonRepository
): Int{
    pokeRepo.findAll().forEach{
        if(it.name.lowercase() == name.lowercase()){
            return it.id
        }
    }
    return 0
}

fun getTypeIdFromString(
    type: String,
    typeRepo: TypeRepository
): Int {
    typeRepo.findAll().forEach{
        if(it.type.lowercase() == type.lowercase()){
            return it.id
        }
    }
    return 0
}

