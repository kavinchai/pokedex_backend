package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entity.*
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.json.JSONObject
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.io.File


// PokemonCsvParser
@Component
class parseFile {

    // another option
    @EventListener()
    fun loadDatabase(){

    }

    private fun parseCSV(): List<List<String>> {
        val filePath = "src/main/resources/db/changelog/data/"
        val file = filePath + "pokedex.csv"
        return csvReader().readAll(File(file))
    }

    private fun formatString(str: String): MutableList<String?> {
        return str.replace("[\"", "")
            .replace("\"]", "")
            .replace("\", \"", ", ")
            .split(", ")
            .toMutableList<String?>()
    }

    // remove unused methods
    private fun getPokemonTypes(){
        val pokemonInfo: List<List<String>> = parseCSV()

    }


    // Is this method init Pokemon Entity? Is the name accurate. Is the return type useful?
    private fun initPokemonEntity(): Array<MutableList<out Any>> {
        val pokemonInfo: List<List<String>> = parseCSV()
        val pokeTypeList = mutableListOf<PokemonTypes>()
        val pokeAbilityList = mutableListOf<PokemonAbilities>()
        val eggGroupList = mutableListOf<EggGroups>()
        val pokeStatList = mutableListOf<PokemonStats>()

        for (columns in 1 until pokemonInfo.size) {
            val types = formatString(pokemonInfo[columns][2])
            val ability = formatString(pokemonInfo[columns][5])
            val eggGroup = formatString(pokemonInfo[columns][6])
            val pokeStat = JSONObject(pokemonInfo[columns][7])

            if (types.size == 1) types.add(null)
            if (ability.size == 1) for (i in 1..2) ability.add(null)
            if (ability.size == 2) ability.add(null)
            if (eggGroup.size == 1) eggGroup.add(null)

            pokeTypeList.add(
                PokemonTypes(
                    pokemonInfo[columns][0].toInt(),
                    types[0],
                    types[1]
                )
            )
            pokeAbilityList.add(
                PokemonAbilities(
                    pokemonInfo[columns][0].toInt(),
                    ability[0],
                    ability[1],
                    ability[2]
                )
            )
            eggGroupList.add(
                EggGroups(
                    pokemonInfo[columns][0].toInt(),
                    eggGroup[0],
                    eggGroup[1]
                )
            )
            pokeStatList.add(
                PokemonStats(
                    pokemonInfo[columns][0].toInt(),
                    pokeStat.get("hp") as Int,
                    pokeStat.get("speed") as Int,
                    pokeStat.get("attack") as Int,
                    pokeStat.get("defense") as Int,
                    pokeStat.get("special-attack") as Int,
                    pokeStat.get("special-defense") as Int
                )
            )
        }
        return arrayOf(pokeTypeList, pokeAbilityList, eggGroupList, pokeStatList)
    }

    fun listOfPokemon(): List<Pokemon> {
        val pokemonInfo: List<List<String>> = parseCSV()
        val pokemonList = mutableListOf<Pokemon>()

        // good use of destructuring for cleanliness, but this is error prone.
        val (pokeTypeList,
            pokeAbilityList,
            eggGroupList,
            pokeStatList) = initPokemonEntity()

        // what are these numbers?
        for (columns in 1 until pokemonInfo.size) {
            val newPokemon: Pokemon = Pokemon(
                pokemonInfo[columns][0].toInt(),
                pokemonInfo[columns][1],
                pokeTypeList[columns - 1] as PokemonTypes,
                pokemonInfo[columns][3].toDouble(),
                pokemonInfo[columns][4].toDouble(),
                pokeAbilityList[columns - 1] as PokemonAbilities,
                eggGroupList[columns - 1] as EggGroups,
                pokeStatList[columns - 1] as PokemonStats,
                pokemonInfo[columns][8],
                pokemonInfo[columns][9]
            )
            pokemonList.add(newPokemon)
        }
        return pokemonList
    }
}