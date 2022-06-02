package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.controller.repository.PokemonRepository
import com.bushelpowered.pokedex.dataClasses.*
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.io.File
import java.util.Optional

@Service
class PokemonService (val db: PokemonRepository) {
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

    fun pokemonTypeEntity(): List<PokemonTypes> {
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

    fun pokemonAbilityEntity(): List<PokemonAbilities> {
        val pokemonChar:List<List<String>> = parseCSV()
        val pokeAbilityList = mutableListOf<PokemonAbilities>()

        for (entity in 1 until pokemonChar.size) {
            var ability = formatString(pokemonChar[entity][5])

            if (ability.size == 1) {
                ability.add(null)
            }

            pokeAbilityList.add(
                PokemonAbilities(
                    pokemonChar[entity][0].toInt(),
                    ability[0],
                    ability[1],
                )
            )
        }
        return pokeAbilityList
    }

    fun eggGroupEntity(): List<EggGroups> {
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

    fun pokemonStatEntity(): List<PokemonStats> {
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

    fun allPokemon(): MutableIterable<Pokemon> {
        val pokemonChar:List<List<String>> = parseCSV()
        for (entity in 1 until pokemonChar.size) {

            val newPokemon: Pokemon = Pokemon(
                pokemonChar[entity][0].toInt(),
                pokemonChar[entity][1],
                pokemonChar[entity][3].toDouble(),
                pokemonChar[entity][4].toDouble(),
                pokemonChar[entity][8],
                pokemonChar[entity][9],
                pokemonStatEntity()[entity-1],
                eggGroupEntity()[entity - 1],
                pokemonAbilityEntity()[entity - 1],
                pokemonTypeEntity()[entity - 1]
            )

            db.save(newPokemon)
        }
        return db.findAll()
    }

    fun findPokemon(id: Int): Optional<Pokemon> {
        return db.findById(id)
    }

}