package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.model.PokemonGenus
import org.springframework.data.repository.CrudRepository

interface PokemonGenusRepository : CrudRepository<PokemonGenus, Int>