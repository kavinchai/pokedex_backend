package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.PokemonEggGroup
import org.springframework.data.repository.CrudRepository

interface PokemonEggGroupRepository : CrudRepository<PokemonEggGroup, Int>