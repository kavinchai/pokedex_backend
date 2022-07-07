package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.TrainerResponse
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.service.TrainerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.bushelpowered.pokedex.utils.toTrainerResponse
import org.springframework.http.HttpStatus

@RestController
class TrainerController(private val trainerService: TrainerService) {
    @GetMapping("/trainer")
    fun getAllTrainers(): ResponseEntity<List<TrainerResponse>> {
        val trainersList = trainerService.getAllTrainers()
        val trainerResponseList = mutableListOf<TrainerResponse>()
        trainersList.forEach { trainer ->
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
        return ResponseEntity(
            trainerService.createTrainer(trainerInfo), HttpStatus.CREATED
        )
    }

    @PutMapping("/trainer")
    fun updateTrainerById(
        @RequestBody trainerInfo: HashMap<String, Any>
    ): ResponseEntity<Unit> {
        return if (
            trainerInfo.containsKey("id") &&
            trainerInfo.containsKey("username") &&
            trainerInfo.containsKey("firstname") &&
            trainerInfo.containsKey("lastname") &&
            trainerInfo.containsKey("email")
        ) {
            ResponseEntity.ok(
                trainerService.updateTrainerById(trainerInfo)
            )
        } else {
            ResponseEntity(HttpStatus.NOT_ACCEPTABLE)
        }

    }

    @DeleteMapping("/trainer")
    fun deleteTrainerById(
        @RequestBody trainerId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity.ok(
            trainerService.deleteTrainer(trainerId)
        )
    }
}