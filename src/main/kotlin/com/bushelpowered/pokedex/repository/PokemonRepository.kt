package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.model.Pokemon
import org.springframework.data.repository.CrudRepository

interface PokemonRepository : CrudRepository<Pokemon, Int>{
    fun existsByName(name: String): Boolean

    fun findByName(name: String): Pokemon
}