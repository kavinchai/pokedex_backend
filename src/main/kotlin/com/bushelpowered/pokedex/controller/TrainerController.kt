package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.TrainerResponse
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.service.TrainerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TrainerController(private val trainerService: TrainerService) {

    /*
        Remove the trailing slash on the get mapping
        Again, we are returning a NotFound

        This endpoint is not required in the spec & should be removed.
     */
    @GetMapping("/trainer/")
    fun getAllTrainers(): ResponseEntity<List<Trainer>> {
        return ResponseEntity(
            trainerService.getAllTrainers(),
            HttpStatus.NOT_FOUND
        )
    }

    /*
        Again, we are returning a NotFound

        This endpoint is not required in the spec & should be removed.
    */
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
    /*
        Should remove the trailing slash

        This method should be renamed to reflect its actions
        Trainer should be TrainerRequest
    */
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

    /*
        We should have all of the info in path variables in the request body
        With TLS & Networks in general, the paths in which you interact are
        somewhat visible, less secury than the "request body".

        If someone was snooping on your network calls, they can see your trainerId
    */
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

    /*
        This is a good use case for ResponseEntity.noContent()

        In general, again, we would have the id in a jwt for security, but here,
        for this use case, having id in the path is fine
    */
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