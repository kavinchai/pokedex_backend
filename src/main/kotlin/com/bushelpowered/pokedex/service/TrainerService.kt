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

    fun createTrainer(trainerInfo: TrainerRequest): Trainer {
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
        trainerRepository.save(Trainer(
            id = trainerInfo.id,
            username = trainerInfo.username,
            firstname = trainerInfo.firstname,
            lastname = trainerInfo.lastname,
            email = trainerInfo.email,
            capturedPokemon = listOf()
        ))
        val trainerId = trainerRepository.findByUsername(trainerInfo.username).id
        return Trainer(
            id = trainerId,
            username = trainerInfo.username,
            firstname = trainerInfo.firstname,
            lastname = trainerInfo.lastname,
            email = trainerInfo.email,
            capturedPokemon = listOf()
        )
    }

    // Design choice: Users cannot update their captured pokemon
    //                They can only update their:
    //                username, firstname, lastname, email
    fun updateTrainerById(trainerInfo: TrainerRequest): Trainer {
        val trainer = trainerRepository.findById(trainerInfo.id)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Error: Trainer does not exist"
                )
            }
        println(trainer.capturedPokemon)
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
        return Trainer(
            id = trainer.id,
            username = trainerInfo.username,
            firstname = trainerInfo.firstname,
            lastname = trainerInfo.lastname,
            email = trainerInfo.email,
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

