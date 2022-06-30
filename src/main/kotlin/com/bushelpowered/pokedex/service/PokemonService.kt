package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.entity.*
import com.bushelpowered.pokedex.repository.TypeRepository
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonService(
    private val pokemonRepository: PokemonRepository,
    private val typeRepository: TypeRepository,
    private val pokemonTypesRepository: TypeRepository
){
    fun allPokemon(): List<Pokemon> {
        return pokemonRepository.findAll().toList()
    }

    fun getPokemonById(id: Int): Pokemon? {
       return pokemonRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun getPokemonByName(name: String): Any {
        val pokemonList =  pokemonRepository.findAll()
        for (pokemon in pokemonList){
            if (pokemon.name.lowercase() == name.lowercase()){
                return pokemon
            }
        }
        return ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getPokemonByPage(pageNum: Int, pageSize: Int): Iterable<Pokemon> {
        return pokemonRepository.findAll(PageRequest.of(pageNum, pageSize))
    }
}
