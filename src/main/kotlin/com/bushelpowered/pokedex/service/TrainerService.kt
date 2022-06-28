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
    private val capturedPokemonDb: CapturedPokemonRepository
) {
    fun getAllTrainers(): List<Trainer> {
        // we don't want to do 'as' this will type cast at runtime, and can cause issues
        // we want to explicitly map
        // return trainerDb.findAll().toList()
        return trainerDb.findAll() as List<Trainer>
    }

    // the service should return a model.
    fun getTrainer(id: Int): TrainerResponse? {
        val trainer: Trainer? = trainerDb.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        // good use of let, but "trainer" should never be null here.

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
        // in "one" line (should be broken up across lines for readability)
        // return trainerDb.findById(id)?.let { TrainerResponse(....) } ?: throw Exception()
    }

    // What if we fail to save the user? How can we handle that and return a message to the user.
    // e.g. what if I create an account with the same username / email.
    fun createTrainer(trainerInfo: Trainer) {
        trainerDb.save(trainerInfo)
    }


    // Same comments as above
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
        val tmpTrainer: Trainer = trainerDb.findById(trainerId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val tmpPokemon: Pokemon =  pokemonDb.findById(pokemonId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        // we are doubling up on queries, where we should just assign in the block above.

        // JPA should be able to manage the "pokemonId to capturedPokemon" if setup correctly
        // what happens if I capture 2 of the same pokemon?
        val uniqueTrainerPokemon = (tmpTrainer.trainerId * 1000) + tmpPokemon.pokemonId
        capturedPokemonDb.save(CapturedPokemon(uniqueTrainerPokemon, trainerId, pokemonId))
    }

    fun deleteTrainer(trainerId: Int) {
        trainerDb.findById(trainerId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        trainerDb.deleteById(trainerId)
    }
}

