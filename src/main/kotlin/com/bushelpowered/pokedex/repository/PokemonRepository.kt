package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.Pokemon
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface PokemonRepository : CrudRepository<Pokemon, Int>