package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entity.*
import com.bushelpowered.pokedex.repository.AbilityRepository
import com.bushelpowered.pokedex.repository.PokemonAbilityRepository
import com.bushelpowered.pokedex.repository.PokemonTypeRepository
import com.bushelpowered.pokedex.repository.TypeRepository
import java.util.*
import kotlin.collections.HashMap

class PopulateData(
    private val typeRepository: TypeRepository,
    private val pokemonTypesRepository: PokemonTypeRepository,
    private val abilityRepository: AbilityRepository,
    private val pokemonAbilityRepository: PokemonAbilityRepository,
) {
    private fun getUniqueTypes(): List<String> {
        val pokemonInfo: List<List<String>> = ParseFile().parseCSV()
        val pokeTypeList = mutableListOf<String>()
        for (columns in 1 until pokemonInfo.size) {
            val types = ParseFile().formatString(pokemonInfo[columns][2])
            types.forEach{
                if (it != null) {
                    pokeTypeList.add(it)
                }
            }
        }
        return pokeTypeList.distinct()
    }

    private fun getUniqueAbilities(): List<String> {
        val pokemonInfo: List<List<String>> = ParseFile().parseCSV()
        val pokeAbilityList = mutableListOf<String>()
        for (columns in 1 until pokemonInfo.size) {
            val types = ParseFile().formatString(pokemonInfo[columns][5])
            types.forEach{
                if (it != null) {
                    pokeAbilityList.add(it)
                }
            }
        }
        return pokeAbilityList.distinct()
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

    fun populateTypesTable(): List<Type> {
        val uniqueList = getUniqueTypes()
        val typeList = mutableListOf<Type>()
        for (type in uniqueList.indices) {
            typeList.add(
                Type(
                    type + 1,
                    uniqueList[type]
                )
            )
        }
        return typeList
    }


    fun populatePokemonTypesTable(): List<PokemonType> {
        val pokemonInfo: List<List<String>> = ParseFile().parseCSV()
        val pokemonTypeList = mutableListOf<PokemonType>()
        val typeDb = typeRepository.findAll()
        val typeListMap : MutableMap<Int, String> = HashMap()
        var uniqueId : Int = 1;
        typeDb.forEach {
            typeListMap[it.id] = it.type.toString()
        }
        for (pokemonId in 1 until pokemonInfo.size) {
            val individualPokemonTypes = ParseFile().formatString(pokemonInfo[pokemonId][2])
            for (type in individualPokemonTypes) {
                if (typeListMap.containsValue(type)){
//                    pokemonTypesRepository.save(PokemonTypes(uniqueId, pokemonId, type.toString()))
                    pokemonTypeList.add(PokemonType(uniqueId, pokemonId, getKey(typeListMap, type.toString())))
                    uniqueId += 1

                }
            }
        }
        return pokemonTypeList
    }

    fun populateAbilityTable(): List<Ability> {
        val uniqueList = getUniqueAbilities()
        val typeList = mutableListOf<Ability>()
        for (ability in uniqueList.indices) {
            typeList.add(
                Ability(
                    ability + 1,
                    uniqueList[ability]
                )
            )
        }
        return typeList
    }

    fun populatePokemonAbilityTable(): List<PokemonAbility> {
        val pokemonInfo: List<List<String>> = ParseFile().parseCSV()
        val pokemonAbilityList = mutableListOf<PokemonAbility>()
        val abilityDb = abilityRepository.findAll()
        val abilityMap : MutableMap<Int, String> = HashMap()
        var uniqueId : Int = 1;
        abilityDb.forEach {
            abilityMap[it.id] = it.ability.toString()
        }
        for (pokemonId in 1 until pokemonInfo.size) {
            val individualPokemonAbilities = ParseFile().formatString(pokemonInfo[pokemonId][5])
            for (ability in individualPokemonAbilities) {
                if (abilityMap.containsValue(ability)){
                    pokemonAbilityList.add(PokemonAbility(uniqueId, pokemonId, getKey(abilityMap, ability.toString())))
                    uniqueId += 1
                }
            }
        }
        return pokemonAbilityList
    }


    fun listOfPokemon(): List<Pokemon> {
        val pokemonTypeDb = pokemonTypesRepository.findAll()
        val pokemonInfo: List<List<String>> = ParseFile().parseCSV()
        val pokemonList = mutableListOf<Pokemon>()
        val (
            eggGroupList,
            pokeStatList
        ) = ParseFile().initPokemonEntity()

        for (pokemonId in 1 until pokemonInfo.size) {
            val typeList = mutableListOf<Type>()
            for (type in pokemonTypeDb){
                if (type.pokemonId == pokemonId){
                    val typeEntity = type.typeId?.let { typeRepository.findById(it) }
                    typeList.add(typeEntity)
                }
            }
            val abilityList = mutableListOf<Ability>()
            val pokemonAbilityDb = pokemonAbilityRepository.findAll()
            for (ability in pokemonAbilityDb){
                if (ability.pokemonId == pokemonId){
                    val abilityEntity = ability.abilityId?.let { abilityRepository.findById(it) }
                    abilityList.add(abilityEntity)
                }
            }
            val newPokemon: Pokemon = Pokemon(
                pokemonInfo[pokemonId][0].toInt(),
                pokemonInfo[pokemonId][1],
                typeList,
                pokemonInfo[pokemonId][3].toDouble(),
                pokemonInfo[pokemonId][4].toDouble(),
                abilityList,
                eggGroupList[pokemonId - 1] as EggGroup,
                pokeStatList[pokemonId - 1] as PokemonStats,
                pokemonInfo[pokemonId][8],
                pokemonInfo[pokemonId][9]
            )
            pokemonList.add(newPokemon)
        }
        return pokemonList
    }

    private fun <E> MutableList<E>.add(element: Optional<E>?) {

    }

}
