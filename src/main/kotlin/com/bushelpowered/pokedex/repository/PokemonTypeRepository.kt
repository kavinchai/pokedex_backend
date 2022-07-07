package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.PokemonAbility
import com.bushelpowered.pokedex.entity.PokemonType
import org.springframework.data.repository.CrudRepository

interface PokemonTypeRepository : CrudRepository<PokemonType, Int>