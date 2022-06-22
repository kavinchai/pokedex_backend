package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.dataClasses.*
import org.apache.coyote.Response
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.Optional

@Service
class PokemonService(val pokemonDb: PokemonRepository) {
    fun createPokemonDb() {
        pokemonDb.saveAll(parseFile().listOfPokemon())
    }

    fun allPokemon(): Iterable<Pokemon> {
        return pokemonDb.findAll()
    }

    fun getPokemon(id: Int): Pokemon? {
        return pokemonDb.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun getPokemonByPage(pageNum: Int, pageSize: Int): Iterable<Pokemon> {
        return pokemonDb.findAll(PageRequest.of(pageNum, pageSize))
    }
    private fun isValidIntList(strList: List<String>): Boolean {
        strList.forEach {
            if (it.toIntOrNull() == null) {  // Not int type
                return false
            }
            if (it.toInt() > pokemonDb.count()) {  // PokemonId exceeds pokedex
                return false
            }
        }
        return true
    }
}