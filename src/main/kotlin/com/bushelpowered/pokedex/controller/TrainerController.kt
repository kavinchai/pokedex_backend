package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.request.TrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.dto.response.TrainerResponse
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.utils.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus

@RestController
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/trainer")
    fun createTrainer(
        @RequestBody trainerRequest: TrainerRequest
    ): ResponseEntity<TrainerResponse> {
        val trainerModel = trainerService.createTrainer(trainerRequest)
        return ResponseEntity(
            trainerModel.toResponse(),
             HttpStatus.CREATED
        )
    }

    @PutMapping("/trainer")
    fun updateTrainerById(
        @RequestBody trainerRequest: TrainerRequest
    ): ResponseEntity<TrainerResponse> {
        val trainerModel = trainerService.updateTrainerById(trainerRequest)
        return ResponseEntity.ok(
            trainerModel.toResponse()
        )
    }

    @DeleteMapping("/trainer")
    fun deleteTrainerById(
        @RequestBody deleteTrainerRequest: DeleteTrainerRequest
    ): ResponseEntity<TrainerResponse> {
        val trainerModel = trainerService.deleteTrainer(deleteTrainerRequest)
        return ResponseEntity.ok(
            trainerModel.toResponse()
        )
    }
}