package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.model.Ability
import org.springframework.data.repository.CrudRepository

interface AbilityRepository : CrudRepository<Ability, Int>