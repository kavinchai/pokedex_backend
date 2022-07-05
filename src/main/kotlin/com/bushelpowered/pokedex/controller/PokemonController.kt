package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.PokemonResponse
import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.service.PokemonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PokemonController(
    private val pokemonService: PokemonService
) {
    @GetMapping("/pokemon")
    fun searchPokemon(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "15") pageSize: Int,
        @RequestParam name:String?,
        @RequestParam id: Int?,
    ): ResponseEntity<out Any> {
        if (name != null && id == null){
            return ResponseEntity.ok(listOf(pokemonService.getPokemonByName(name)))
        }
        else if (name == null && id != null){
            return ResponseEntity.ok(pokemonService.getPokemonById(id))
        }
        return ResponseEntity.ok(pokemonService.getPokemonByPage(pageNum, pageSize))
    }

    @GetMapping("/pokemon/{id}")
    fun getPokemonById(
        @PathVariable id: Int
    ): Any {
        val pokemon = pokemonService.getPokemonById(id) ?: return ResponseEntity.notFound()
        return ResponseEntity.ok(
            pokemon.toResponse()
        )
    }

    fun Pokemon.toResponse(): PokemonResponse {
        return PokemonResponse(
            id = this.id,
            name = this.name,
            height = this.height,
            weight = this.weight,
            stats = this.stats,
            description = this.description
        )
    }
}
