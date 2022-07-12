package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.model.Trainer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainerRepository : CrudRepository<Trainer, Int> {
    fun existsByEmail(email: String): Boolean
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): Trainer
    fun findByEmail(email: String): Trainer
}