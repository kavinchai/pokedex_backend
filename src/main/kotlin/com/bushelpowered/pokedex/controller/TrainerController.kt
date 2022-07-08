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
        // you can still add validation with Valiktor on the repsonse body
        // Trainer should be a TrainerRequest, not a Trainer Entity.


        return ResponseEntity(
            trainerService.createTrainer(trainerInfo), HttpStatus.CREATED
        )
    }

    @PutMapping("/trainer")
    fun updateTrainerById(
        @RequestBody trainerInfo: HashMap<String, Any> // Trainer should be a TrainerRequest, not a HashMap
    ): ResponseEntity<Any> {
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
        @RequestBody trainerId: Int // Trainer should be a DeleteTrainerRequest, not an int
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(
            trainerService.deleteTrainer(trainerId)
        )
    }
}