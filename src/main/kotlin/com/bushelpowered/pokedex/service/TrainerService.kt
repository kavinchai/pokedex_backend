package com.bushelpowered.pokedex.service

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
    private val trainerRepository: TrainerRepository,
    private val pokemonRepository: PokemonRepository,
    private val capturedPokemonRepository: CapturedPokemonRepository
) {
    fun getAllTrainers(): List<Trainer> {
        return trainerRepository.findAll().toList()
    }

    fun getTrainer(id: Int): Trainer? {
        return trainerRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun createTrainer(trainerInfo: Trainer) {
        trainerRepository.save(trainerInfo)
    }

    fun updateTrainerById(id: Int, trainerInfo: Trainer) {
        trainerRepository.findById((id)).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        trainerRepository.save(
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
        val tmpTrainer: Trainer =
            trainerRepository.findById(trainerId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val tmpPokemon: Pokemon =
            pokemonRepository.findById(pokemonId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val uniqueTrainerPokemon = (tmpTrainer.id * 1000) + tmpPokemon.id
        capturedPokemonRepository.save(CapturedPokemon(uniqueTrainerPokemon, trainerId, pokemonId))
    }

    fun deleteTrainer(trainerId: Int) {
        trainerRepository.findById(trainerId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        trainerRepository.deleteById(trainerId)
    }
}

