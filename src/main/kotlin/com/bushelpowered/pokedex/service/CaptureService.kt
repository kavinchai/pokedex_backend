package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.request.CapturePokemonRequest
import com.bushelpowered.pokedex.entity.CapturedPokemon
import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.TrainerRepository
import com.bushelpowered.pokedex.repository.CapturedPokemonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CaptureService(
    private val trainerRepository: TrainerRepository,
    private val pokemonRepository: PokemonRepository,
    private val capturedPokemonRepository: CapturedPokemonRepository
) {
    fun capturePokemonToTrainer(captureInfo: CapturePokemonRequest): CapturedPokemon {
        val trainerId = captureInfo.trainerId
        val pokemonId = captureInfo.pokemonId
        val trainer = trainerRepository
            .findById(trainerId)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Error: Trainer does not exist"
                )
            }
        if (!pokemonRepository.existsById(pokemonId)) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Error: Pokemon does not exist"
            )
        }

        val uniqueCapturedPokemonId = (trainer.id * 1000) + pokemonId
        if (!capturedPokemonRepository.existsById(uniqueCapturedPokemonId)) {
            capturedPokemonRepository.save(CapturedPokemon(uniqueCapturedPokemonId, trainerId, pokemonId, 1))
        } else {
            val timesCaught = capturedPokemonRepository.findById(uniqueCapturedPokemonId).orElse(null).timesCaught
            capturedPokemonRepository
                .save(
                    CapturedPokemon(
                        uniqueCapturedPokemonId,
                        trainerId,
                        pokemonId,
                        timesCaught + 1
                    )
                )
        }
        return capturedPokemonRepository.findById(uniqueCapturedPokemonId).orElseThrow()
    }
}