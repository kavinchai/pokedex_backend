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
        return trainerDb.findAll() as List<Trainer>
    }

    fun getTrainer(id: Int): Trainer? {
        return trainerDb.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun createTrainer(trainerInfo: Trainer) {
        trainerDb.save(trainerInfo)
    }

    fun updateTrainerById(id: Int, trainerInfo: Trainer) {
        trainerDb.findById((id)).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        trainerDb.save(
            Trainer(
                id = trainerInfo.id,
                username = trainerInfo.username,
                firstname = trainerInfo.firstname,
                lastname = trainerInfo.lastname,
                email = trainerInfo.email,
                capturedPokemon = trainerInfo.capturedPokemon
            )
        )
    }

    fun capturePokemonToTrainer(trainerId: Int, pokemonId: Int) {
        val tmpTrainer: Trainer =trainerDb.findById(trainerId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val tmpPokemon: Pokemon = pokemonDb.findById(pokemonId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val uniqueTrainerPokemon = (tmpTrainer.id * 1000) + tmpPokemon.id
        capturedPokemonDb.save(CapturedPokemon(uniqueTrainerPokemon, trainerId, pokemonId))
    }

    fun deleteTrainer(trainerId: Int) {
        trainerDb.findById(trainerId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        trainerDb.deleteById(trainerId)
    }
}

