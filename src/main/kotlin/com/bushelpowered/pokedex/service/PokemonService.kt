package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.entity.Type
import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.PokemonTypeRepository
import com.bushelpowered.pokedex.repository.TypeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import com.bushelpowered.pokedex.utils.checkValidType
import com.bushelpowered.pokedex.utils.getTypeIdFromString
import com.bushelpowered.pokedex.utils.checkValidPokemon
import com.bushelpowered.pokedex.utils.getPokeIdFromString

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
        return pokemonRepository.findById(id)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Error: Pokemon does not exist"
                )
            }
    }

    fun getPokemonByName(name: String): Pokemon? {
        if (!checkValidPokemon(name, pokemonRepository)){   // Check valid pokemon
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Error: Pokemon does not exist"
            )
        }
        val pokemonId = getPokeIdFromString(name, pokemonRepository)
        return pokemonRepository.findById(pokemonId).orElse(null)
    }

    fun getPokemonByType(type: String, type2: String?): List<Pokemon> {
        if (!checkValidType(type, typeRepository)) {    // Check type 1 is valid type
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Error: Invalid Type"
            )
        }

        val pokemonTypeList = pokemonTypeRepository.findAll()
        val typeId = getTypeIdFromString(type, typeRepository)
        val tmpList = mutableListOf<Pokemon>()
        val listOfPokemon = mutableListOf<Pokemon>()
        for (entity in pokemonTypeList) { // Search Pokemon type table for type id
            if (entity.typeId == typeId) {
                val pokemon = pokemonRepository
                    .findById(entity.pokemonId)
                    .orElse(null)
                listOfPokemon.add(pokemon)
            }
        }

        if (type2 != null) {    // Filter for two types
            if (!checkValidType(type2, typeRepository)){    // Check if second type is valid
                throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Error: Invalid Type"
                )
            }
            if (type.lowercase() == type2!!.lowercase()){   // Check if type1 == type2
                throw ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Error: Duplicate Types"
                )
            }
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
