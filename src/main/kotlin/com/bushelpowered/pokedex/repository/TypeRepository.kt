package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.Type
import org.springframework.data.repository.CrudRepository

interface TypeRepository : CrudRepository<Type, Int>{
    fun existsByType(type: String): Boolean

    fun findByType(type: String): Type
}
