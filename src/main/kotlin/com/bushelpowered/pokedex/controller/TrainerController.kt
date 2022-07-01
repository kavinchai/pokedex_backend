package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.TrainerResponse
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.service.TrainerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TrainerController(private val trainerService: TrainerService) {
    @GetMapping("/trainer")
    fun getAllTrainers(): ResponseEntity<List<Trainer>> {
        return ResponseEntity.ok(
            trainerService.getAllTrainers()
        )
    }

    @GetMapping("/trainer/{id}")
    fun getTrainerById(
        @PathVariable id: Int
    ): ResponseEntity<Trainer> {
        val trainerModel: Trainer? = trainerService.getTrainer(id)
        return ResponseEntity.ok(
            trainerModel
        )
    }

    @PostMapping("/trainer")
    fun createTrainer(
        @RequestBody trainerInfo: Trainer
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(
            trainerService.createTrainer(trainerInfo)
        )
    }

    @PutMapping("/trainer/{id}")
    fun updateTrainerById(
        @PathVariable("id") trainerId: Int,
        @RequestBody trainerInfo: Trainer
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(
            trainerService.updateTrainerById(trainerId, trainerInfo)
        )
    }

    @PutMapping("/trainer/{trainerId}/capturePokemon/{pokemonId}")
    fun capturePokemon(
        @PathVariable trainerId: Int,
        @PathVariable pokemonId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(
            trainerService.capturePokemonToTrainer(trainerId, pokemonId)
        )
    }

    @DeleteMapping("/trainer/{id}")
    fun deleteTrainerById(
        @PathVariable("id") trainerId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(
            trainerService.deleteTrainer(trainerId)
        )
    }

    fun Trainer.toResponse(): TrainerResponse {
        return TrainerResponse(
            id = this.id,
            username = this.username,
            firstname = this.firstname,
            lastname = this.lastname,
            email = this.email,
            capturedPokemon = this.capturedPokemon
        )
    }
}