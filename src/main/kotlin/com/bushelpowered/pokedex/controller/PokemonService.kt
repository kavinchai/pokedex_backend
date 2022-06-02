package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.dataClasses.*
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PokemonService (val db: PokemonRepository) {
    fun createPokemonDb(){
        val pokemonList = parseFile().listOfPokemon()
        pokemonList.forEach{pokemon->
            db.save(pokemon)
        }
    }

    fun allPokemon(): MutableIterable<Pokemon> {
        createPokemonDb()
        return db.findAll()
    }

    fun findPokemon(id: Int): Optional<Pokemon> {
        return db.findById(id)
    }

}