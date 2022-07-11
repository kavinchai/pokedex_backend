package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.request.TrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.dto.response.TrainerResponse
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.service.TrainerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus

@RestController
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/trainer")
    fun createTrainer(
        @RequestBody trainerInfo: TrainerRequest
    ): ResponseEntity<TrainerResponse> {
        return ResponseEntity(
            trainerService.createTrainer(trainerInfo), HttpStatus.CREATED
        )
    }

    @PutMapping("/trainer")
    fun updateTrainerById(
        @RequestBody trainerInfo: TrainerRequest
    ): ResponseEntity<TrainerResponse> {
        return ResponseEntity.ok(
            trainerService.updateTrainerById(trainerInfo)
        )
    }

    @DeleteMapping("/trainer")
    fun deleteTrainerById(
        @RequestBody deleteTrainerRequest: DeleteTrainerRequest
    ): ResponseEntity<TrainerResponse> {
        return ResponseEntity.ok(
            trainerService.deleteTrainer(deleteTrainerRequest)
        )
    }
}