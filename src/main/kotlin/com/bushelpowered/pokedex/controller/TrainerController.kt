package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.request.CreateTrainerRequest
import com.bushelpowered.pokedex.dto.request.UpdateTrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.dto.response.TrainerResponse
import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.utils.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus

@RestController
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/trainer")
    fun createTrainer(
        @RequestBody trainerRequest: CreateTrainerRequest
    ): ResponseEntity<TrainerResponse> {
        val trainerModel = trainerService.createTrainer(trainerRequest)
        return ResponseEntity(
            trainerModel.toResponse(),
             HttpStatus.CREATED
        )
    }

    @PutMapping("/trainer")
    fun updateTrainerById(
        @RequestBody trainerRequest: UpdateTrainerRequest
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