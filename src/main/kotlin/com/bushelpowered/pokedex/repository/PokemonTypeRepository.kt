package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.model.PokemonType
import org.springframework.data.repository.CrudRepository

interface PokemonTypeRepository : CrudRepository<PokemonType, Int>