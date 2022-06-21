package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.dataClasses.CapturedPokemon
import org.springframework.data.repository.CrudRepository

interface CapturedPokemonRepository: CrudRepository<CapturedPokemon, Int> {
}