package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entity.*
import com.bushelpowered.pokedex.repository.PokemonTypeRepository
import com.bushelpowered.pokedex.repository.TypeRepository
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.json.JSONObject
import java.io.File
import java.util.*
import kotlin.collections.HashMap

class ParseFile (
    private val typeRepository: TypeRepository,
    private val pokemonTypesRepository: PokemonTypeRepository
){
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

    fun getUniqueTypes(): List<String> {
        val pokemonInfo: List<List<String>> = parseCSV()
        val pokeTypeList = mutableListOf<String>()
        for (columns in 1 until pokemonInfo.size) {
            val types = formatString(pokemonInfo[columns][2])
            types.forEach{
                if (it != null) {
                    pokeTypeList.add(it)
                }
            }
        }
        return pokeTypeList.distinct()
    }



    private fun initPokemonEntity(): Array<MutableList<out Any>> {
        val pokemonInfo: List<List<String>> = parseCSV()
        val pokeAbilityList = mutableListOf<PokemonAbilities>()
        val eggGroupList = mutableListOf<EggGroups>()
        val pokeStatList = mutableListOf<PokemonStats>()

        for (columns in 1 until pokemonInfo.size) {
            val ability = formatString(pokemonInfo[columns][5])
            val eggGroup = formatString(pokemonInfo[columns][6])
            val pokeStat = JSONObject(pokemonInfo[columns][7])

            if (ability.size == 1) for (i in 1..2) ability.add(null)
            if (ability.size == 2) ability.add(null)
            if (eggGroup.size == 1) eggGroup.add(null)


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
        return arrayOf(pokeAbilityList, eggGroupList, pokeStatList)
    }

    fun listOfPokemon(): List<Pokemon> {
        val pokemonInfo: List<List<String>> = parseCSV()
        val pokemonList = mutableListOf<Pokemon>()
        val (
            pokeAbilityList,
            eggGroupList,
            pokeStatList
        ) = initPokemonEntity()

        for (pokemonId in 1 until pokemonInfo.size) {
            val typesList = mutableListOf<Types>()
            val pokemonTypeDb = pokemonTypesRepository.findAll()
            for (entity in pokemonTypeDb){
                if (entity.pokemonId == pokemonId){
                    val typeEntity = entity.typeId?.let { typeRepository.findById(it) }
                    typesList.add(typeEntity)
                }
            }
            val newPokemon: Pokemon = Pokemon(
                pokemonInfo[pokemonId][0].toInt(),
                pokemonInfo[pokemonId][1],
                typesList,
                pokemonInfo[pokemonId][3].toDouble(),
                pokemonInfo[pokemonId][4].toDouble(),
                pokeAbilityList[pokemonId - 1] as PokemonAbilities,
                eggGroupList[pokemonId - 1] as EggGroups,
                pokeStatList[pokemonId - 1] as PokemonStats,
                pokemonInfo[pokemonId][8],
                pokemonInfo[pokemonId][9]
            )
            pokemonList.add(newPokemon)
        }
        return pokemonList
    }
}

private fun <E> MutableList<E>.add(element: Optional<E>?) {

}
