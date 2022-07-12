package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.model.CapturedPokemon
import org.springframework.data.repository.CrudRepository

interface CapturedPokemonRepository : CrudRepository<CapturedPokemon, Int>{
    fun findByTrainer(id: Int): CapturedPokemon
}