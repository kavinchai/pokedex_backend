package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.PokemonResponse
import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.entity.*
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonService(
        // pokemonRepository
    private val pokemonDb: PokemonRepository){
    fun createPokemonDb() {
        pokemonDb.saveAll(parseFile().listOfPokemon())
    }

    fun allPokemon(): List<Pokemon> {
        return pokemonDb.findAll().toList()
    }

    fun getPokemonById(id: Int): PokemonResponse? {
        val pokemon =  pokemonDb.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        return PokemonResponse(
            pokemonId = pokemon.pokemonId,
            name = pokemon.name,
            pokemonTypes = pokemon.pokemonTypes,
            height = pokemon.height,
            weight = pokemon.weight,
            pokemonAbilities = pokemon.pokemonAbilities,
            eggGroups = pokemon.eggGroups,
            pokemonStats = pokemon.pokemonStats,
            genus = pokemon.genus,
            description = pokemon.description
        )
    }

    fun getPokemonByName(name: String): PokemonResponse {
        val pokemonList =  pokemonDb.findAll()
        for (pokemon in pokemonList){
            if (pokemon.name.lowercase() == name.lowercase()){
                return PokemonResponse(
                    pokemonId = pokemon.pokemonId,
                    name = pokemon.name,
                    pokemonTypes = pokemon.pokemonTypes,
                    height = pokemon.height,
                    weight = pokemon.weight,
                    pokemonAbilities = pokemon.pokemonAbilities,
                    eggGroups = pokemon.eggGroups,
                    pokemonStats = pokemon.pokemonStats,
                    genus = pokemon.genus,
                    description = pokemon.description
                )
            }
        }
        throw RuntimeException("Not Found") // ideally a custom exception
    }

    fun getPokemonByPage(pageNum: Int, pageSize: Int): Iterable<Pokemon> {
        return pokemonDb.findAll(PageRequest.of(pageNum, pageSize))
    }
}
