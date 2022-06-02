package com.bushelpowered.pokedex.controller.repository

import com.bushelpowered.pokedex.dataClasses.Pokemon
import org.springframework.data.repository.CrudRepository
import java.util.ArrayList

interface PokemonRepository : CrudRepository<Pokemon, Int> {

}