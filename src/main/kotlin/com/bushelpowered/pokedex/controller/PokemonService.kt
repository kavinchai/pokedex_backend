package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.dataClasses.*
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PokemonService (val db: PokemonRepository) {
    fun createPokemonDb() {
        db.saveAll(parseFile().listOfPokemon())
    }

    fun allPokemon(): MutableIterable<Pokemon> = db.findAll()

    fun getPokemon(id: Int): Optional<Pokemon> = db.findById(id)

}