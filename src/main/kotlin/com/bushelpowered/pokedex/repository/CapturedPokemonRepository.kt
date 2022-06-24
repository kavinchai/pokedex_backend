package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.CapturedPokemon
import org.springframework.data.repository.CrudRepository

interface CapturedPokemonRepository : CrudRepository<CapturedPokemon, Int> {
}