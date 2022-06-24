package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.TrainerResponse
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.service.TrainerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TrainerController(private val trainerService: TrainerService) {
    @GetMapping("/trainer/")
    fun getAllTrainers(): ResponseEntity<List<Trainer>> {
        return ResponseEntity(
            trainerService.getAllTrainers(),
            HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("/trainer/{id}")
    fun getTrainerById(
        @PathVariable id: Int
    ): ResponseEntity<TrainerResponse> {
        val trainerResponse: TrainerResponse? = trainerService.getTrainer(id)
        return ResponseEntity(
            trainerResponse,
            HttpStatus.NOT_FOUND
        )
    }

    @PostMapping("/trainer/")
    fun createEmployee(
        @RequestBody trainerInfo: Trainer
    ): ResponseEntity<Unit> {
        return ResponseEntity(
            trainerService.createTrainer(trainerInfo),
            HttpStatus.CREATED
        )
    }

    @PutMapping("/trainer/{id}")
    fun updateTrainerById(
        @PathVariable("id") trainerId: Int,
        @RequestBody trainerInfo: Trainer
    ): ResponseEntity<Unit> {
        return ResponseEntity(
            trainerService.updateTrainerById(trainerId, trainerInfo),
            HttpStatus.OK
        )
    }

    @PutMapping("/trainer/{trainerId}/capturePokemon/{pokemonId}")
    fun capturePokemon(
        @PathVariable trainerId: Int,
        @PathVariable pokemonId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity(
            trainerService.capturePokemonToTrainer(trainerId, pokemonId),
            HttpStatus.OK
        )
    }

    @DeleteMapping("/trainer/{id}")
    fun deleteTrainerById(
        @PathVariable("id") trainerId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity(
            trainerService.deleteTrainer(trainerId),
            HttpStatus.OK
        )
    }
}