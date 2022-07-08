package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.service.TrainerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus

@RestController
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/trainer")
    fun createTrainer(@RequestBody trainerInfo: Trainer): ResponseEntity<Any> {
        return ResponseEntity(
            trainerService.createTrainer(trainerInfo), HttpStatus.CREATED
        )
    }

    @PutMapping("/trainer")
    fun updateTrainerById(
        @RequestBody trainerInfo: Trainer
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(
            trainerService.updateTrainerById(trainerInfo)
        )
    }

    @DeleteMapping("/trainer")
    fun deleteTrainerById(
        @RequestBody trainerId: Int
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(
            trainerService.deleteTrainer(trainerId)
        )
    }
}