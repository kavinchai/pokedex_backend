package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.entity.Type
import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.PokemonTypeRepository
import com.bushelpowered.pokedex.repository.TypeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonService(
    private val pokemonRepository: PokemonRepository,
    private val pokemonTypeRepository: PokemonTypeRepository,
    private val typeRepository: TypeRepository
) {
    fun getAllPokemon(): List<Pokemon> {
        return pokemonRepository.findAll().toList()
    }

    fun getPokemonById(id: Int): Pokemon? {
        return pokemonRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun getPokemonByName(name: String): Pokemon? {
        val pokemonList = pokemonRepository.findAll()
        val listOfPokemonId = mutableListOf<Int>()
        val listOfTypeModels = mutableListOf<Type>()
        for (pokemon in pokemonList) {
            if (pokemon.name.lowercase() == name.lowercase()) {
                val pokemonId = pokemon.id
                val pokemonTypeRepository = pokemonTypeRepository.findAll()
                for (i in pokemonTypeRepository) {
                    if (i.pokemonId == pokemonId) {
                        listOfPokemonId.add(i.typeId)
                    }
                }
                for (e in listOfPokemonId) {
                    val pokemonType = typeRepository.findById(e).orElse(null)
                    listOfTypeModels.add(pokemonType)
                }
                return pokemon
            }
        }
        return null
    }

    fun getPokemonByType(type: String, type2: String?): List<Pokemon> {
        val typeList = typeRepository.findAll()
        val pokemonTypeList = pokemonTypeRepository.findAll()
        val tmpList = mutableListOf<Pokemon>()
        val listOfPokemon = mutableListOf<Pokemon>()
        for (typeEntity in typeList) { // Check if type input param is valid
            if (type.lowercase() == typeEntity.type.lowercase()) {
                for (entity in pokemonTypeList) { // Search Pokemon type table for type id
                    if (entity.typeId == typeEntity.id) {
                        val pokemon = pokemonRepository.findById(entity.pokemonId).orElse(null)
                        listOfPokemon.add(pokemon)
                    }
                }
            }
        }

        if (type2 != null) {    // Filter for two types
            listOfPokemon.removeAll {
                it.type.size != 2
            }
            for (pokemon in listOfPokemon) {
                if (pokemon.type.size == 2) {
                    if (
                        !(pokemon.type[0].type == type && pokemon.type[1].type == type2) &&
                        !(pokemon.type[0].type == type2 && pokemon.type[1].type == type)
                    ) {
                        tmpList.add(pokemon)   // List of Pokemon without specified types
                    }
                }
            }
            val keysOfTmp = tmpList.map { it.name }
            listOfPokemon.removeAll {
                it.name in keysOfTmp    // Remove Pokemon without specified types
            }
        }

        return listOfPokemon.toList()
    }


}
