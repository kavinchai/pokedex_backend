package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.model.PokemonAbility
import org.springframework.data.repository.CrudRepository

interface PokemonAbilityRepository : CrudRepository<PokemonAbility, Int>