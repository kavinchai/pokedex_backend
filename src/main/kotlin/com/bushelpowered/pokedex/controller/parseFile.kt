package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.*
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.json.JSONObject
import java.io.File

class parseFile {
    private fun parseCSV(): List<List<String>> {
        val filePath = "src/main/resources/db/changelog/data/"
        val file = filePath + "pokedex.csv"
        return csvReader().readAll(File(file))
    }

    private fun formatString(str: String) : MutableList<String?> {
        return str.replace("[\"", "")
            .replace("\"]", "")
            .replace("\", \"", ", ")
            .split(", ")
            .toMutableList<String?>()
    }

    private fun pokemonTypeEntity(): List<PokemonTypes> {
        val pokemonChar:List<List<String>> = parseCSV()
        val pokeTypeList = mutableListOf<PokemonTypes>()

        for (entity in 1 until pokemonChar.size) {
            var types = formatString(pokemonChar[entity][2])

            if (types.size == 1) {
                types.add(null)
            }

            pokeTypeList.add(
                PokemonTypes(
                    pokemonChar[entity][0].toInt(),
                    types[0],
                    types[1],
                )
            )
        }
        return pokeTypeList
    }

    private fun pokemonAbilityEntity(): List<PokemonAbilities> {
        val pokemonChar:List<List<String>> = parseCSV()
        val pokeAbilityList = mutableListOf<PokemonAbilities>()

        for (entity in 1 until pokemonChar.size) {
            var ability = formatString(pokemonChar[entity][5])
            if (ability.size == 1) {
                ability.add(null)
                ability.add(null)
            }

            if (ability.size == 2){
                ability.add(null)
            }

            pokeAbilityList.add(
                PokemonAbilities(
                    pokemonChar[entity][0].toInt(),
                    ability[0],
                    ability[1],
                    ability[2]
                )
            )
        }
        return pokeAbilityList
    }

    private fun eggGroupEntity(): List<EggGroups> {
        val pokemonChar:List<List<String>> = parseCSV()
        val pokeAbilityList = mutableListOf<EggGroups>()
        for (entity in 1 until pokemonChar.size) {
            var eggGroup = formatString(pokemonChar[entity][6])

            if (eggGroup.size == 1) {
                eggGroup.add(null)
            }

            pokeAbilityList.add(
                EggGroups(
                    pokemonChar[entity][0].toInt(),
                    eggGroup[0],
                    eggGroup[1],
                )
            )
        }
        return pokeAbilityList
    }

    private fun pokemonStatEntity(): List<PokemonStats> {
        val pokemonChar:List<List<String>> = parseCSV()
        val pokeStatList = mutableListOf<PokemonStats>()
        for (entity in 1 until pokemonChar.size) {
            var pokeStat = JSONObject(pokemonChar[entity][7])
            pokeStatList.add(
                PokemonStats(
                    pokemonChar[entity][0].toInt(),
                    pokeStat.get("hp") as Int,
                    pokeStat.get("speed") as Int,
                    pokeStat.get("attack") as Int,
                    pokeStat.get("defense") as Int,
                    pokeStat.get("special-attack") as Int,
                    pokeStat.get("special-defense") as Int,
                )
            )
        }
        return pokeStatList
    }

    fun listOfPokemon(): List<Pokemon>{
        val pokemonChar:List<List<String>> = parseCSV()
        val pokemonList = mutableListOf<Pokemon>()
        for (entity in 1 until pokemonChar.size) {
            val newPokemon: Pokemon = Pokemon(
                pokemonChar[entity][0].toInt(),
                pokemonChar[entity][1],
                pokemonTypeEntity()[entity - 1],
                pokemonChar[entity][3].toDouble(),
                pokemonChar[entity][4].toDouble(),
                pokemonAbilityEntity()[entity - 1],
                eggGroupEntity()[entity - 1],
                pokemonStatEntity()[entity-1],
                pokemonChar[entity][8],
                pokemonChar[entity][9]
            )
            pokemonList.add(newPokemon)
        }
        return pokemonList
    }


}