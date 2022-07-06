package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.PokemonResponse
import com.bushelpowered.pokedex.service.PokemonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import com.bushelpowered.pokedex.utils.toResponse
import com.bushelpowered.pokedex.utils.paginate

@RestController
class PokemonController(
    private val pokemonService: PokemonService
) {
    @GetMapping("/pokemon")
    fun searchPokemon(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "15") pageSize: Int,
        @RequestParam name: String?,
        @RequestParam id: Int?,
    ): ResponseEntity<Any> {

        return if (name != null && id == null) {    // name param provided
            ResponseEntity.ok(
                listOf(
                    pokemonService.getPokemonByName(name)?.toResponse() ?: ResponseEntity.notFound()
                )
            )
        } else if (name == null && id != null) {   // id param provided
            ResponseEntity.ok(
                pokemonService.getPokemonById(id)?.toResponse() ?: ResponseEntity.notFound()
            )
        } else {
            val pokemonList = mutableListOf<PokemonResponse>()

            pokemonService.getAllPokemon().forEach {
                pokemonList.add(it.toResponse())
            }

            return ResponseEntity.ok(paginate(pageNum, pageSize, pokemonList))
        }
    }

    @GetMapping("/type")
    fun searchPokemonType(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "15") pageSize: Int,
        @RequestParam type: String?,
        @RequestParam type2: String?
    ): ResponseEntity<Any> {
        val listOfPokemonResponse = mutableListOf<PokemonResponse>()

        return if (type != null) {
            pokemonService.getPokemonByType(type, type2).forEach {
                listOfPokemonResponse.add(it.toResponse())
            }

            return ResponseEntity.ok(paginate(pageNum, pageSize, listOfPokemonResponse))
        } else {
            pokemonService.getAllPokemon().forEach {
                listOfPokemonResponse.add(it.toResponse())
            }

            return ResponseEntity.ok(paginate(pageNum, pageSize, listOfPokemonResponse))
        }
    }
}