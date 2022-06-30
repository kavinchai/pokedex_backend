package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.PokemonAbility
import org.springframework.data.repository.CrudRepository

interface PokemonAbilityRepository:CrudRepository<PokemonAbility, Int>