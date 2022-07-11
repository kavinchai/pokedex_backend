package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.request.TrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.dto.response.TrainerResponse
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.repository.TrainerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class TrainerService(private val trainerRepository: TrainerRepository) {

    fun createTrainer(trainerInfo: TrainerRequest): TrainerResponse {
        val trainerEmail = trainerInfo.email
        val trainerUserName = trainerInfo.username
        if (trainerRepository.existsByEmail(trainerEmail)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Error: Email already exists"
            )
        }
        if (trainerRepository.existsByUsername(trainerUserName)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Error: Username already exists"
            )
        }
        val trainerModel = Trainer(
            id = trainerInfo.id,
            username = trainerInfo.username,
            firstname = trainerInfo.firstname,
            lastname = trainerInfo.lastname,
            email = trainerInfo.email,
            capturedPokemon = listOf()
        )
        trainerRepository.save(trainerModel)
        val trainerId = trainerRepository.findByUsername(trainerInfo.username).id
        return TrainerResponse(
            id = trainerId,
            username = trainerInfo.username,
            firstname = trainerInfo.firstname,
            lastname = trainerInfo.lastname,
            email = trainerInfo.email
        )
    }

    // Design choice: Users cannot update their captured pokemon
    //                They can only update their:
    //                username, firstname, lastname, email
    fun updateTrainerById(trainerInfo: TrainerRequest): TrainerResponse {
        val trainer = trainerRepository.findById(trainerInfo.id)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Error: Trainer does not exist"
                )
            }
        trainerRepository.save(
            Trainer(
                id = trainerInfo.id,
                username = trainerInfo.username,
                firstname = trainerInfo.firstname,
                lastname = trainerInfo.lastname,
                email = trainerInfo.email,
                capturedPokemon = trainer.capturedPokemon
            )
        )
        return TrainerResponse(
            id = trainerInfo.id,
            username = trainerInfo.username,
            firstname = trainerInfo.firstname,
            lastname = trainerInfo.lastname,
            email = trainerInfo.email
        )
    }

    fun deleteTrainer(deleteTrainerRequest: DeleteTrainerRequest): TrainerResponse {
        val trainerId = deleteTrainerRequest.id
        val trainer = trainerRepository.findById(trainerId).orElseThrow {
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Error: Trainer does not exist"
            )
        }
        trainerRepository.deleteById(trainerId)
        return TrainerResponse(
            id = trainer.id,
            username = trainer.username,
            firstname = trainer.firstname,
            lastname = trainer.lastname,
            email = trainer.email
        )
    }
}

