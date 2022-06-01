package com.bushelpowered.pokedex.controller.repository

import com.bushelpowered.pokedex.dataClasses.Pokemon
import org.springframework.data.repository.CrudRepository

interface PokemonRepository : CrudRepository<Pokemon, Int> {

}