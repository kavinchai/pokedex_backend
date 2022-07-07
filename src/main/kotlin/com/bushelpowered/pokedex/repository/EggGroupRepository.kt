package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.EggGroup
import org.springframework.data.repository.CrudRepository

interface EggGroupRepository : CrudRepository<EggGroup, Int>