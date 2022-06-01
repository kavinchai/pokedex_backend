package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.Pokemon
import com.bushelpowered.pokedex.repository.PokemonRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PokemonService (val db: PokemonRepository){

    fun allPokemon(): MutableIterable<Pokemon> = db.findAll()

    fun findPokemon(id: Int): Optional<Pokemon> = db.findById(id)

}