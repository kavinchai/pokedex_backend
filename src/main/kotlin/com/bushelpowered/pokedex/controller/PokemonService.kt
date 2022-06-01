package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.Pokemon
import com.bushelpowered.pokedex.controller.repository.PokemonRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PokemonService (val db: PokemonRepository){

    fun allPokemon(): MutableIterable<Pokemon> {
        val createPokemon = parseFile()

        db.save(createPokemon.pokemonArray())
        return db.findAll()
    }

    fun findPokemon(id: Int): Optional<Pokemon> {
        return db.findById(id)
    }

}