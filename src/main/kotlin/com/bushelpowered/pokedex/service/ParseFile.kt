package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entity.*
import com.bushelpowered.pokedex.repository.PokemonTypeRepository
import com.bushelpowered.pokedex.repository.TypeRepository
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.json.JSONObject
import java.io.File
import java.util.*

class ParseFile (){
    fun parseCSV(): List<List<String>> {
        val filePath = "src/main/resources/db/changelog/data/"
        val file = filePath + "pokedex.csv"
        return csvReader().readAll(File(file))
    }

    fun formatString(str: String): MutableList<String?> {
        return str.replace("[\"", "")
            .replace("\"]", "")
            .replace("\", \"", ", ")
            .split(", ")
            .toMutableList<String?>()
    }



    fun initPokemonEntity(): Array<MutableList<out Any>> {
        val pokemonInfo: List<List<String>> = parseCSV()
        val pokeAbilityList = mutableListOf<Ability>()
        val eggGroupList = mutableListOf<EggGroups>()
        val pokeStatList = mutableListOf<PokemonStats>()

        for (columns in 1 until pokemonInfo.size) {
            val ability = formatString(pokemonInfo[columns][5])
            val eggGroup = formatString(pokemonInfo[columns][6])
            val pokeStat = JSONObject(pokemonInfo[columns][7])

            if (ability.size == 1) for (i in 1..2) ability.add(null)
            if (ability.size == 2) ability.add(null)
            if (eggGroup.size == 1) eggGroup.add(null)

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
        return arrayOf(eggGroupList, pokeStatList)
    }
}

