package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.Trainer
import org.springframework.data.repository.CrudRepository

interface TrainerRepository : CrudRepository<Trainer, Int>