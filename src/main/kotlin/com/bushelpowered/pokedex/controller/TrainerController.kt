package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.TrainerResponse
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.utils.toTrainerResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TrainerController(private val trainerService: TrainerService) {
    @GetMapping("/trainer")
    fun getAllTrainers(): ResponseEntity<List<TrainerResponse>> {
        val trainersList = trainerService.getAllTrainers()
        val trainerResponseList = mutableListOf<TrainerResponse>()
        trainersList.forEach{trainer->
            trainerResponseList.add(trainer.toTrainerResponse())
        }
        return ResponseEntity.ok(
            trainerResponseList
        )
    }

    @GetMapping("/trainer/{id}")
    fun getTrainerById(
        @PathVariable id: Int
    ): ResponseEntity<TrainerResponse> {
        val trainerModel: Trainer? = trainerService.getTrainer(id)
        return ResponseEntity.ok(
            trainerModel?.toTrainerResponse()
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

    @DeleteMapping("/trainer/{id}")
    fun deleteTrainerById(
        @PathVariable("id") trainerId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(
            trainerService.deleteTrainer(trainerId)
        )
    }


}