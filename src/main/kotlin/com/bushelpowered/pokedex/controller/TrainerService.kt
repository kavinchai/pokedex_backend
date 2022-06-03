package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.Pokemon
import com.bushelpowered.pokedex.dataClasses.Trainer
import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.TrainerRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class TrainerService (val tdb: TrainerRepository, val pdb: PokemonRepository){
    fun getAllTrainers(): MutableIterable<Trainer> = tdb.findAll()

    fun getTrainer(id: Int): Optional<Trainer> {
        return tdb.findById(id)
    }

    fun getTrainerPokemon(id: Int): MutableIterable<Pokemon> {
        val pokemonIdList = tdb.findById(id).get().capturedPokemon
            ?.split(" ")
        val intList : MutableList<Int> = mutableListOf()

        if (pokemonIdList != null) {
            pokemonIdList.forEach{
                intList.add(it.toInt())
            }
        }
        return pdb.findAllById(intList)
    }

    fun createTrainer(trainerInfo: Trainer) = tdb.save(trainerInfo)

    private fun isValidIntList(strList : List<String>) : Boolean{
        strList.forEach{
            println(it.toInt())
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
        if (!trainerInfo.capturedPokemon.isNullOrEmpty()){  // If capturedPokemon changes are not null
            val capturedPokemonStr = trainerInfo.capturedPokemon
                ?.split(" ")
            if (isValidIntList(capturedPokemonStr) == true) {
                if (hasDuplicates(capturedPokemonStr) == false){
                    if (tdb.existsById(id)) {
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
                else{
                    println("Error: Pokemon ID has duplicates")
                }
            }
            else{
                println("Error: Invalid pokemon id")
            }
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