package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.PokemonGenus
import org.springframework.data.repository.CrudRepository

interface PokemonGenusRepository : CrudRepository<PokemonGenus, Int>