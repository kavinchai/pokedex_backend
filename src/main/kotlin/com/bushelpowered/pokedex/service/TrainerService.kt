package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.DeleteTrainerRequest
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.repository.TrainerRepository
import com.bushelpowered.pokedex.repository.PokemonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class TrainerService(
    private val trainerRepository: TrainerRepository,
    private val pokemonRepository: PokemonRepository //  unused value
) {

    fun createTrainer(trainerInfo: Trainer) {
        val trainerEmail = trainerInfo.email
        val trainerUserName = trainerInfo.username
        if (trainerRepository.existsByEmail(trainerEmail)){
            throw ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE, // we would use "Bad Request" here
                "Error: Email already exists"
            )
        }
        if (trainerRepository.existsByUsername(trainerUserName)){
            throw ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE,  // we would use "Bad Request" here
                "Error: Username already exists"
            )
        }

        trainerRepository.save(trainerInfo)
    }

    // Design choice: Users cannot update their captured pokemon
    //                They can only update their:
    //                username, firstname, lastname, email
    fun updateTrainerById(trainerInfo: Trainer) {
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
    }

    fun deleteTrainer(deleteTrainerRequest: DeleteTrainerRequest) {
        val trainerId = deleteTrainerRequest.id
        trainerRepository.findById(trainerId).orElseThrow {
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Error: Trainer does not exist"
            )
        }
        trainerRepository.deleteById(trainerId)
    }
}

