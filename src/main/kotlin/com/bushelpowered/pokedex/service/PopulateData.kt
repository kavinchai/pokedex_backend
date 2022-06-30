package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entity.PokemonTypes
import com.bushelpowered.pokedex.entity.Types
import com.bushelpowered.pokedex.repository.PokemonTypeRepository
import com.bushelpowered.pokedex.repository.TypeRepository

class PopulateData(
    private val typeRepository: TypeRepository,
    private val pokemonTypesRepository: PokemonTypeRepository
) {
    fun populateTypesTable(): List<Types> {
        val uniqueList = ParseFile(typeRepository, pokemonTypesRepository).getUniqueTypes()
        val typesList = mutableListOf<Types>()
        for (type in uniqueList.indices) {
            typesList.add(
                Types(
                    type + 1,
                    uniqueList[type]
                )
            )
        }
        return typesList
    }

    private fun <K, V> getKey(map: Map<K, V>, target: V): K? {
        for ((key, value) in map)
        {
            if (target == value) {
                return key
            }
        }
        return null
    }
    fun populatePokemonTypesTable(): List<PokemonTypes> {
        val pokemonInfo: List<List<String>> = ParseFile(typeRepository, pokemonTypesRepository).parseCSV()
        val pokemonTypesList = mutableListOf<PokemonTypes>()
        val typeDb = typeRepository.findAll()
        val typeListName = mutableListOf<String>()
        val typeListMap : MutableMap<Int, String> = HashMap()
        var uniqueId : Int = 1;
        typeDb.forEach {
            typeListName.add(it.type.toString())
            typeListMap[it.id] = it.type.toString()
        }
        for (pokemonId in 1 until pokemonInfo.size) {
            val individualPokemonTypes = ParseFile(typeRepository, pokemonTypesRepository).formatString(pokemonInfo[pokemonId][2])
            for (type in individualPokemonTypes) {
                if (typeListName.contains(type)){
//                    pokemonTypesRepository.save(PokemonTypes(uniqueId, pokemonId, type.toString()))
                    pokemonTypesList.add(PokemonTypes(uniqueId, pokemonId, getKey(typeListMap, type.toString())))
                    uniqueId += 1

                }
            }
        }
        return pokemonTypesList
    }
}