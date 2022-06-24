package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.TrainerResponse
import com.bushelpowered.pokedex.entity.CapturedPokemon
import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.repository.CapturedPokemonRepository
import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.TrainerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class TrainerService(
    private val trainerDb: TrainerRepository,
    private val pokemonDb: PokemonRepository,
    private val capturedPokemonDb: CapturedPokemonRepository,
) {
    fun getAllTrainers(): List<Trainer> {
        return trainerDb.findAll() as List<Trainer>
    }

    fun getTrainer(id: Int): TrainerResponse? {
        val trainer: Trainer? = trainerDb.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        return trainer?.let {
            TrainerResponse(
                trainerId = it.trainerId,
                userName = trainer.userName,
                firstName = trainer.firstName,
                lastName = trainer.lastName,
                emailId = trainer.emailId,
                capturedPokemon = trainer.capturedPokemon
            )
        }
    }

    fun createTrainer(trainerInfo: Trainer) {
        trainerDb.save(trainerInfo)
    }

    fun updateTrainerById(id: Int, trainerInfo: Trainer) {
        trainerDb.findById((id)).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        trainerDb.save(
            Trainer(
                trainerId = trainerInfo.trainerId,
                userName = trainerInfo.userName,
                firstName = trainerInfo.firstName,
                lastName = trainerInfo.lastName,
                emailId = trainerInfo.emailId,
                capturedPokemon = trainerInfo.capturedPokemon
            )
        )
    }

    fun capturePokemonToTrainer(trainerId: Int, pokemonId: Int) {
        trainerDb.findById(trainerId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        pokemonDb.findById(pokemonId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val tmpTrainer: Trainer = trainerDb.findById(trainerId).orElse(null)
        val tmpPokemon: Pokemon = pokemonDb.findById(pokemonId).orElse(null)
        val uniqueTrainerPokemon = (tmpTrainer.trainerId * 1000) + tmpPokemon.pokemonId
        capturedPokemonDb.save(CapturedPokemon(uniqueTrainerPokemon, trainerId, pokemonId))
    }

    fun deleteTrainer(trainerId: Int) {
        trainerDb.findById(trainerId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        trainerDb.deleteById(trainerId)
    }
}

