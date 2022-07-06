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
class CaptureService(
    private val trainerRepository: TrainerRepository,
    private val pokemonRepository: PokemonRepository,
    private val capturedPokemonRepository: CapturedPokemonRepository
) {


    private fun checkValidPokemonList(pokemonList: List<Int>): Boolean {
        pokemonList.forEach{pokemonId ->
            if (!pokemonRepository.existsById(pokemonId)){
                return false
            }
        }
        return true
    }

    fun capturePokemonToTrainer(captureInfo: HashMap<String, Any>) {
        val trainerId: Int = captureInfo.getValue("trainerId") as Int
        val listOfPokemonId: List<Int> = captureInfo.getValue("capturedPokemon") as List<Int>
        println(trainerId)
        println(listOfPokemonId)
        val tmpTrainer: Trainer = trainerRepository
            .findById(trainerId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        if (checkValidPokemonList(listOfPokemonId)){
            println("Here")
            listOfPokemonId.forEach { pokemonId ->
                println("Here2")
                val uniqueTrainerPokemon = (tmpTrainer.id * 1000) + pokemonId
                capturedPokemonRepository.save(CapturedPokemon(uniqueTrainerPokemon, trainerId, pokemonId))
            }
        }
    }
}