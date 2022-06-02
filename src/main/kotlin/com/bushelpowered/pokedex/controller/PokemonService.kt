package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.controller.repository.PokemonRepository
import com.bushelpowered.pokedex.dataClasses.*
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.io.File
import java.util.Optional

@Service
class PokemonService (val db: PokemonRepository) {


    fun allPokemon(): MutableIterable<Pokemon> {
        val pokemonList = parseFile().pokemonEntity()
        pokemonList.forEach{pokemon->
            db.save(pokemon)
        }
        return db.findAll()
    }

    fun findPokemon(id: Int): Optional<Pokemon> {
        return db.findById(id)
    }

}