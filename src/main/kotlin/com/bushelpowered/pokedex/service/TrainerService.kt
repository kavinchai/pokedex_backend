package com.bushelpowered.pokedex.service

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
    private val pokemonRepository: PokemonRepository
) {

    fun createTrainer(trainerInfo: Trainer) {
        val trainerEmail = trainerInfo.email
        val trainerUserName = trainerInfo.username
        val trainerRepo = trainerRepository.findAll()

        trainerRepo.forEach {
            if (it.username == trainerUserName) {
                throw ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Error: Username already exists"
                )
            }
            if (it.email == trainerEmail) {
                throw ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Error: Trainer already exists"
                )
            }
        }
        trainerRepository.save(trainerInfo)
    }

    // Design choice: Users cannot update their captured pokemon
    //                They can only update their:
    //                username, firstname, lastname, email
    fun updateTrainerById(trainerInfo: HashMap<String, Any>) {
        try {
            val trainerId: Int = trainerInfo.getValue("id") as Int
            val trainerUser: String = trainerInfo.getValue("username" ) as String
            val trainerFirstName: String = trainerInfo.getValue("firstname") as String
            val trainerLastName: String = trainerInfo.getValue("lastname") as String
            val trainerEmail: String = trainerInfo.getValue("email") as String
            val trainer = trainerRepository.findById(trainerId)
                .orElseThrow {
                    ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Error: Trainer does not exist"
                    )
                }
            trainerRepository.save(
                Trainer(
                    id = trainer.id,
                    username = trainerUser,
                    firstname = trainerFirstName,
                    lastname = trainerLastName,
                    email = trainerEmail,
                    capturedPokemon = trainer.capturedPokemon
                )
            )
        } catch (e: Exception){
            println(e.toString())
            throw ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE,
                "Error: $e"
            )
        }
    }

    fun deleteTrainer(trainerId: Int) {
        trainerRepository.findById(trainerId).orElseThrow {
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Error: Trainer does not exist"
            )
        }
        trainerRepository.deleteById(trainerId)
    }
}

