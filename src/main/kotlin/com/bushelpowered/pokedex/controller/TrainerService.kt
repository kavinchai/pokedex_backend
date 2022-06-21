package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.CapturedPokemon
import com.bushelpowered.pokedex.dataClasses.Pokemon
import com.bushelpowered.pokedex.dataClasses.Trainer
import com.bushelpowered.pokedex.repository.CapturedPokemonRepository
import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.TrainerRepository
import org.springframework.stereotype.Service

@Service
class TrainerService (val tdb: TrainerRepository, val pdb: PokemonRepository, val cpdb: CapturedPokemonRepository){
    fun getAllTrainers(): Iterable<Trainer> = tdb.findAll()

    fun getTrainer(id: Int): Trainer? = tdb.findById(id).orElse(null)

    fun getCaptured(): Iterable<CapturedPokemon> = cpdb.findAll()

    fun createTrainer(trainerInfo: Trainer) {
        tdb.save(trainerInfo)
    }

    private fun isValidIntList(strList : List<String>) : Boolean{
        strList.forEach{
            if (it.toIntOrNull() == null){  // Not int type
                return false
            }
            if (it.toInt() > pdb.count()){  // PokemonId exceeds pokedex
                return false
            }
        }
        return true
    }

    private fun hasDuplicates(strList : List<String>) : Boolean{
        return strList.size != strList.distinct().count()
    }
    fun updateTrainerById(id: Int, trainerInfo: Trainer) {
        if (tdb.existsById(id)) {
            println(trainerInfo)
            tdb.save(
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
        else {
            println("Error: Trainer does not exist")
        }

    }

    fun capturePokemonToTrainer(trainerId: Int, pokemonId: Int){
        if (tdb.existsById(trainerId)){
            if (pdb.existsById(pokemonId)){
                val tmpTrainer : Trainer = tdb.findById(trainerId).orElse(null)
                val tmpPokemon : Pokemon = pdb.findById(pokemonId).orElse(null)
                val uniqueTrainerPokemon = (tmpTrainer.trainerId * 1000) + tmpPokemon.pokemonId
                cpdb.save(CapturedPokemon(uniqueTrainerPokemon, trainerId, pokemonId))
            }
            else{
                println("Error: Pokemon doesn't exist")
            }
        }
        else{
            println("Error: Trainer doesn't exist")
        }
    }

    fun deleteTrainer(trainerId: Int){
        if (tdb.existsById(trainerId)){
            tdb.deleteById(trainerId)
        }
        else{
            println("Error: Trainer doesn't exist")
        }
    }
}