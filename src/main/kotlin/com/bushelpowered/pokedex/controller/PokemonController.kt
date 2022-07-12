package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.PokemonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import com.bushelpowered.pokedex.utils.toPokemonResponse
import com.bushelpowered.pokedex.utils.toPaginatedResponse
import org.springframework.web.server.ResponseStatusException

@RestController
class PokemonController(private val pokemonService: PokemonService) {
    @GetMapping("/pokemon")
    fun searchPokemon(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "15") pageSize: Int,
        @RequestParam name: String?,
        @RequestParam id: Int?,
    ): ResponseEntity<Any> {
        return try {
            if (name != null && id == null) {    // name param provided
                val pokemon = pokemonService.getPokemonByName(name) ?: return ResponseEntity.notFound().build()
                ResponseEntity.ok(
                    pokemon.toPokemonResponse()
                )
            } else if (name == null && id != null) {   // id param provided
                val pokemon = pokemonService.getPokemonById(id) ?: return ResponseEntity.notFound().build()
                ResponseEntity.ok(
                    pokemon.toPokemonResponse()
                )
            } else {
                val pokemonList = pokemonService.getAllPokemon().map { it.toPokemonResponse() }
                ResponseEntity.ok(pokemonList.toPaginatedResponse(pageNum, pageSize))
            }
        } catch (e: ResponseStatusException) {
            ResponseEntity.badRequest().body("Error: ${e.reason}")
        }

    }

    @GetMapping("/type")
    fun searchPokemonByType(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "15") pageSize: Int,
        @RequestParam type: String?,
        @RequestParam type2: String?
    ): ResponseEntity<Any> {
        return try {
            if (type != null) {
                val listOfPokemonResponse = pokemonService.getPokemonByType(type, type2).map {
                    it.toPokemonResponse()
                }
                ResponseEntity.ok(listOfPokemonResponse.toPaginatedResponse(pageNum, pageSize))
            } else {
                val listOfPokemonResponse = pokemonService.getAllPokemon().map {
                    it.toPokemonResponse()
                }
                ResponseEntity.ok(listOfPokemonResponse.toPaginatedResponse(pageNum, pageSize))
            }
        } catch (e: ResponseStatusException) {
            ResponseEntity.badRequest().body("Error: ${e.reason}")
        }

    }
}