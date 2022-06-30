package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.Types
import org.springframework.data.repository.CrudRepository

interface TypeRepository : CrudRepository<Types, Int> {
}
