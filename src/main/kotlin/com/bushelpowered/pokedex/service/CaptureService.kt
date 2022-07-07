package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entity.CapturedPokemon
import com.bushelpowered.pokedex.entity.Trainer
import com.bushelpowered.pokedex.repository.CapturedPokemonRepository
import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.TrainerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CaptureService(
    private val trainerRepository: TrainerRepository,
    private val pokemonRepository: PokemonRepository,
    private val capturedPokemonRepository: CapturedPokemonRepository
) {

    fun capturePokemonToTrainer(captureInfo: HashMap<String, Any>) {
        try {
            val trainerId: Int = captureInfo.getValue("trainerId") as Int
            val listOfPokemonId: List<Int> = captureInfo.getValue("capturedPokemon") as List<Int>
            val tmpTrainer: Trainer = trainerRepository
                .findById(trainerId)
                .orElseThrow {
                    ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Error: Trainer does not exist"
                    )
                }

            if (checkValidPokemonList(listOfPokemonId, pokemonRepository)) {
                listOfPokemonId.forEach { pokemonId ->
                    val uniqueTrainerPokemon = (tmpTrainer.id * 1000) + pokemonId
                    capturedPokemonRepository
                        .save(CapturedPokemon(uniqueTrainerPokemon, trainerId, pokemonId))
                }
            } else {
                throw ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Error: Invalid list of pokemon"
                )
            }
        } catch (e: Exception) {
            println(e.toString())
            throw ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE,
                "Error: $e"
            )
        }
    }

    private fun checkValidPokemonList(
        pokemonList: List<Int>,
        pokemonRepo: PokemonRepository
    ): Boolean {
        pokemonList.forEach { pokemonId ->
            if (!pokemonRepo.existsById(pokemonId)) {
                return false
            }
        }
        return true
    }

}