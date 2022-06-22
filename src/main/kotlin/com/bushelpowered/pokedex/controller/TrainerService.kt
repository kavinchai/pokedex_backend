package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.CapturedPokemon
import com.bushelpowered.pokedex.dataClasses.Pokemon
import com.bushelpowered.pokedex.dataClasses.Trainer
import com.bushelpowered.pokedex.repository.CapturedPokemonRepository
import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.TrainerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TrainerService(
    val trainerDb: TrainerRepository,
    val pokemonDb: PokemonRepository,
    val capturedPokemonDb: CapturedPokemonRepository
) {
    fun getAllTrainers(): Iterable<Trainer> {
        return trainerDb.findAll()
    }

    fun getTrainer(id: Int): Trainer? {
        return trainerDb.findById(id).orElse(null)
    }

    fun createTrainer(trainerInfo: Trainer) {
        trainerDb.save(trainerInfo)
    }

    private fun hasDuplicates(strList: List<String>): Boolean {
        return strList.size != strList.distinct().count()
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