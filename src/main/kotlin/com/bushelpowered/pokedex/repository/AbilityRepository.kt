package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.Ability
import org.springframework.data.repository.CrudRepository

interface AbilityRepository : CrudRepository<Ability, Int>