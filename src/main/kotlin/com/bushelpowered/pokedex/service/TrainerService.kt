package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.request.CreateTrainerRequest
import com.bushelpowered.pokedex.dto.request.UpdateTrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.repository.TrainerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class TrainerService(private val trainerRepository: TrainerRepository) {

    fun createTrainer(createTrainerRequest: CreateTrainerRequest): Trainer {
        val trainerEmail = createTrainerRequest.email
        val trainerUsername = createTrainerRequest.username
        if (trainerRepository.existsByEmail(trainerEmail)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Error: Email already exists"
            )
        }
        if (trainerRepository.existsByUsername(trainerUsername)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Error: Username already exists"
            )
        }
        trainerRepository.save(Trainer(
            id = 0,
            username = createTrainerRequest.username,
            firstname = createTrainerRequest.firstname,
            lastname = createTrainerRequest.lastname,
            email = createTrainerRequest.email,
            capturedPokemon = listOf()
        ))
        val trainerId = trainerRepository.findByUsername(createTrainerRequest.username).id
        return Trainer(
            id = trainerId,
            username = createTrainerRequest.username,
            firstname = createTrainerRequest.firstname,
            lastname = createTrainerRequest.lastname,
            email = createTrainerRequest.email,
            capturedPokemon = listOf()
        )
    }

    fun updateTrainerById(updateTrainerRequest: UpdateTrainerRequest): Trainer {
        val trainerEmail = updateTrainerRequest.email
        val trainerUsername = updateTrainerRequest.username
        if (trainerRepository.existsByEmail(trainerEmail)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Error: Email already exists"
            )
        }
        if (trainerRepository.existsByUsername(trainerUsername)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Error: Username already exists"
            )
        }
        val trainer = trainerRepository.findById(updateTrainerRequest.id)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Error: Trainer does not exist"
                )
            }
        trainerRepository.save(
            Trainer(
                id = updateTrainerRequest.id,
                username = updateTrainerRequest.username,
                firstname = updateTrainerRequest.firstname,
                lastname = updateTrainerRequest.lastname,
                email = updateTrainerRequest.email,
                capturedPokemon = trainer.capturedPokemon
            )
        )
        return Trainer(
            id = trainer.id,
            username = updateTrainerRequest.username,
            firstname = updateTrainerRequest.firstname,
            lastname = updateTrainerRequest.lastname,
            email = updateTrainerRequest.email,
            capturedPokemon = trainer.capturedPokemon
        )
    }

    fun deleteTrainer(deleteTrainerRequest: DeleteTrainerRequest): Trainer {
        val trainerId = deleteTrainerRequest.id
        val trainer = trainerRepository.findById(trainerId).orElseThrow {
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Error: Trainer does not exist"
            )
        }
        trainerRepository.deleteById(trainerId)
        return Trainer(
            id = trainer.id,
            username = trainer.username,
            firstname = trainer.firstname,
            lastname = trainer.lastname,
            email = trainer.email,
            capturedPokemon = trainer.capturedPokemon
        )
    }
}

