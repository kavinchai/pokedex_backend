package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entity.*
import com.bushelpowered.pokedex.repository.*
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

@Component
class PopulateData(
    private val typeRepository: TypeRepository,
    private val pokemonTypesRepository: PokemonTypeRepository,
    private val abilityRepository: AbilityRepository,
    private val pokemonAbilityRepository: PokemonAbilityRepository,
    private val eggGroupRepository: EggGroupRepository,
    private val pokemonEggGroupRepository: PokemonEggGroupRepository,
    private val genusRepository: GenusRepository,
    private val pokemonGenusRepository: PokemonGenusRepository,
    private val pokemonRepository: PokemonRepository
) {

    @EventListener(ApplicationReadyEvent::class) // Import data on startup
    fun populateTables() {
        typeRepository.saveAll(getTypesList())
        pokemonTypesRepository.saveAll(getPokemonTypesList())
        abilityRepository.saveAll(getAbilityList())
        pokemonAbilityRepository.saveAll(getPokemonAbilityList())
        eggGroupRepository.saveAll(getEggGroupList())
        pokemonEggGroupRepository.saveAll(getPokemonEggGroupList())
        genusRepository.saveAll(getGenusList())
        pokemonGenusRepository.saveAll(getPokemonGenusList())
        pokemonRepository.saveAll(getPokemonList())
    }

    private fun <K, V> getKey(map: Map<K, V>, target: V): K? {
        for ((key, value) in map) {
            if (target == value) {
                return key
            }
        }
        return null
    }

    private fun getUniqueTypes(): List<String> {
        val pokemonInfo: List<List<String>> = PokemonCsvParser().parseCSV()
        val pokeTypeList = mutableListOf<String>()
        for (columns in 1 until pokemonInfo.size) {
            val types = PokemonCsvParser().formatToStringList(pokemonInfo[columns][2])
            types.forEach {
                pokeTypeList.add(it)
            }
        }
        return pokeTypeList.distinct()
    }

    private fun getUniqueAbilities(): List<String> {
        val pokemonInfo: List<List<String>> = PokemonCsvParser().parseCSV()
        val pokeAbilityList = mutableListOf<String>()
        for (columns in 1 until pokemonInfo.size) {
            val abilities = PokemonCsvParser().formatToStringList(pokemonInfo[columns][5])
            abilities.forEach {
                pokeAbilityList.add(it)
            }
        }
        return pokeAbilityList.distinct()
    }

    private fun getUniqueEggGroups(): List<String> {
        val pokemonInfo: List<List<String>> = PokemonCsvParser().parseCSV()
        val pokeEggGroupList = mutableListOf<String>()
        for (columns in 1 until pokemonInfo.size) {
            val eggGroups = PokemonCsvParser().formatToStringList(pokemonInfo[columns][6])
            eggGroups.forEach {
                pokeEggGroupList.add(it)
            }
        }
        return pokeEggGroupList.distinct()
    }

    private fun getUniqueGenus(): List<String> {
        val pokemonInfo: List<List<String>> = PokemonCsvParser().parseCSV()
        val pokeGenusList = mutableListOf<String>()
        for (columns in 1 until pokemonInfo.size) {
            val genus = PokemonCsvParser().formatToStringList(pokemonInfo[columns][8])
            genus.forEach {
                pokeGenusList.add(it)
            }
        }
        return pokeGenusList.distinct()
    }


    private fun getTypesList(): List<Type> {
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

    private fun getAbilityList(): List<Ability> {
        val uniqueList = getUniqueAbilities()
        val abilityList = mutableListOf<Ability>()
        for (ability in uniqueList.indices) {
            abilityList.add(
                Ability(
                    ability + 1,
                    uniqueList[ability]
                )
            )
        }
        return abilityList
    }

    private fun getEggGroupList(): List<EggGroup> {
        val uniqueList = getUniqueEggGroups()
        val eggGroupList = mutableListOf<EggGroup>()
        for (eggGroup in uniqueList.indices) {
            eggGroupList.add(
                EggGroup(
                    eggGroup + 1,
                    uniqueList[eggGroup]
                )
            )
        }
        return eggGroupList
    }

    private fun getGenusList(): List<Genus> {
        val uniqueList = getUniqueGenus()
        val genusList = mutableListOf<Genus>()
        for (genus in uniqueList.indices) {
            genusList.add(
                Genus(
                    genus + 1,
                    uniqueList[genus]
                )
            )
        }
        return genusList
    }

    private fun getPokemonTypesList(): List<PokemonType> {
        val pokemonInfo: List<List<String>> = PokemonCsvParser().parseCSV()
        val pokemonTypeList = mutableListOf<PokemonType>()
        val typeDb = typeRepository.findAll()
        val typeListMap: MutableMap<Int, String> = HashMap()
        var uniqueId = 1
        typeDb.forEach {
            typeListMap[it.id] = it.type
        }
        for (pokemonId in 1 until pokemonInfo.size) {
            val individualPokemonTypes = PokemonCsvParser().formatToStringList(pokemonInfo[pokemonId][2])
            for (type in individualPokemonTypes) {
                if (typeListMap.containsValue(type)) {
                    pokemonTypeList.add(PokemonType(uniqueId, pokemonId, getKey(typeListMap, type)!!))
                    uniqueId += 1

                }
            }
        }
        return pokemonTypeList
    }

    private fun getPokemonAbilityList(): List<PokemonAbility> {
        val pokemonInfo: List<List<String>> = PokemonCsvParser().parseCSV()
        val pokemonAbilityList = mutableListOf<PokemonAbility>()
        val abilityDb = abilityRepository.findAll()
        val abilityMap: MutableMap<Int, String> = HashMap()
        var uniqueId = 1
        abilityDb.forEach {
            abilityMap[it.id] = it.ability
        }
        for (pokemonId in 1 until pokemonInfo.size) {
            val individualPokemonAbilities = PokemonCsvParser().formatToStringList(pokemonInfo[pokemonId][5])
            for (ability in individualPokemonAbilities) {
                if (abilityMap.containsValue(ability)) {
                    pokemonAbilityList.add(PokemonAbility(uniqueId, pokemonId, getKey(abilityMap, ability)!!))
                    uniqueId += 1
                }
            }
        }
        return pokemonAbilityList
    }

    private fun getPokemonEggGroupList(): List<PokemonEggGroup> {
        val pokemonInfo: List<List<String>> = PokemonCsvParser().parseCSV()
        val pokemonEggGroupList = mutableListOf<PokemonEggGroup>()
        val eggGroupDb = eggGroupRepository.findAll()
        val eggGroupMap: MutableMap<Int, String> = HashMap()
        var uniqueId = 1
        eggGroupDb.forEach {
            eggGroupMap[it.id] = it.eggGroup
        }
        for (pokemonId in 1 until pokemonInfo.size) {
            val individualPokemonEggGroups = PokemonCsvParser().formatToStringList(pokemonInfo[pokemonId][6])
            for (eggGroup in individualPokemonEggGroups) {
                if (eggGroupMap.containsValue(eggGroup)) {
                    pokemonEggGroupList.add(
                        PokemonEggGroup(
                            uniqueId, pokemonId,
                            getKey(eggGroupMap, eggGroup)!!
                        )
                    )
                    uniqueId += 1
                }
            }
        }
        return pokemonEggGroupList
    }

    private fun getPokemonGenusList(): List<PokemonGenus> {
        val pokemonInfo: List<List<String>> = PokemonCsvParser().parseCSV()
        val pokemonGenusList = mutableListOf<PokemonGenus>()
        val genusDb = genusRepository.findAll()
        val genusMap: MutableMap<Int, String> = HashMap()
        var uniqueId = 1
        genusDb.forEach {
            genusMap[it.id] = it.genus
        }
        for (pokemonId in 1 until pokemonInfo.size) {
            val individualPokemonGenus = PokemonCsvParser().formatToStringList(pokemonInfo[pokemonId][8])
            for (genus in individualPokemonGenus) {
                if (genusMap.containsValue(genus)) {
                    pokemonGenusList.add(PokemonGenus(uniqueId, pokemonId, getKey(genusMap, genus)!!))
                    uniqueId += 1
                }
            }
        }
        return pokemonGenusList
    }

    private fun getPokemonList(): List<Pokemon> {
        val pokemonTypeDb = pokemonTypesRepository.findAll()
        val pokemonAbilityDb = pokemonAbilityRepository.findAll()
        val pokemonEggGroupDb = pokemonEggGroupRepository.findAll()
        val pokemonGenusDb = pokemonGenusRepository.findAll()

        val pokemonInfo: List<List<String>> = PokemonCsvParser().parseCSV()
        val pokemonList = mutableListOf<Pokemon>()
        val pokeStatList = PokemonCsvParser().getPokemonStat()

        for (pokemonId in 1 until pokemonInfo.size) {
            val typeList = mutableListOf<Type>()
            val abilityList = mutableListOf<Ability>()
            val eggGroupList = mutableListOf<EggGroup>()
            val genusList = mutableListOf<Genus>()

            for (type in pokemonTypeDb) {
                if (type.pokemonId == pokemonId) {
                    val typeEntity = type.typeId.let { typeRepository.findById(it) }
                    typeList.add(typeEntity)
                }
            }
            for (ability in pokemonAbilityDb) {
                if (ability.pokemonId == pokemonId) {
                    val abilityEntity = ability.abilityId.let { abilityRepository.findById(it) }
                    abilityList.add(abilityEntity)
                }
            }
            for (eggGroup in pokemonEggGroupDb) {
                if (eggGroup.pokemonId == pokemonId) {
                    val eggGroupEntity = eggGroup.eggGroupId.let { eggGroupRepository.findById(it) }
                    eggGroupList.add(eggGroupEntity)
                }
            }
            for (genus in pokemonGenusDb) {
                if (genus.pokemonId == pokemonId) {
                    val genusEntity = genus.genusId.let { genusRepository.findById(it) }
                    genusList.add(genusEntity)
                }
            }
            val newPokemon = Pokemon(
                pokemonInfo[pokemonId][0].toInt(),
                pokemonInfo[pokemonId][1],
                typeList,
                pokemonInfo[pokemonId][3].toFloat(),
                pokemonInfo[pokemonId][4].toFloat(),
                abilityList,
                eggGroupList,
                pokeStatList[pokemonId - 1],
                genusList,
                pokemonInfo[pokemonId][9]
            )
            pokemonList.add(newPokemon)
        }
        return pokemonList
    }

    private fun <E> MutableList<E>.add(element: Optional<E>) {
    }

}


