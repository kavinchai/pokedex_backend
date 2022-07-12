package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.model.EggGroup
import org.springframework.data.repository.CrudRepository

interface EggGroupRepository : CrudRepository<EggGroup, Int>