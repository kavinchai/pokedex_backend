package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.Genus
import org.springframework.data.repository.CrudRepository

interface GenusRepository : CrudRepository<Genus, Int>