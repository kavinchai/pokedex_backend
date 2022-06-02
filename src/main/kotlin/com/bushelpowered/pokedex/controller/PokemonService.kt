package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.Pokemon
import com.bushelpowered.pokedex.controller.repository.PokemonRepository
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.springframework.stereotype.Service
import java.io.File
import java.util.*

@Service
class PokemonService (val db: PokemonRepository){
    private fun getCSV(): File {
        val filePath = "src/main/resources/db/changelog/data/"
        val file = filePath + "pokedex.csv"
        return File(file)
    }
    fun allPokemon(): MutableIterable<Pokemon> {
        val file = getCSV()
        val pokemonChar: List<List<String>> = csvReader().readAll(file)

        for (entity in 1 until pokemonChar.size){
            val newPokemon: Pokemon = Pokemon(
                pokemonChar[entity][0].toInt(),
                pokemonChar[entity][1],
                pokemonChar[entity][3].toDouble(),
                pokemonChar[entity][4].toDouble(),
                pokemonChar[entity][8],
                pokemonChar[entity][9],
            )

            db.save(newPokemon)
        }

        //db.save(createPokemon.pokemonArray())
        return db.findAll()
    }

    fun findPokemon(id: Int): Optional<Pokemon> {
        return db.findById(id)
    }

}