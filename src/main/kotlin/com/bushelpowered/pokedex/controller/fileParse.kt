package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.controller.repository.PokemonRepository
import com.bushelpowered.pokedex.dataClasses.Pokemon
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class parseFile (){

    private fun getCSV(): File{
        val filePath = "src/main/resources/db/changelog/data/"
        val file = filePath + "pokedex.csv"
        return File(file)
    }
    fun pokemonArray(): ArrayList<Pokemon> {
        val file = getCSV()
        val pokemonChar: List<List<String>> = csvReader().readAll(file)

        var pokemonList = ArrayList<Pokemon>()
        for (entity in 1 until pokemonChar.size){
            //println("id: ${pokemonChar[entity][0]}")
            pokemonList.add(Pokemon(
                pokemonChar[entity][0].toInt(),
                pokemonChar[entity][1],
                pokemonChar[entity][3].toInt(),
                pokemonChar[entity][4].toInt(),
                pokemonChar[entity][8],
                pokemonChar[entity][9],
                ))
        }
        return pokemonList
    }
    // create repository
}