package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.PokemonResponse
import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.service.PokemonService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PokemonController(private val pokemonService: PokemonService) {
    @EventListener(ApplicationReadyEvent::class) // Import data on startup
    fun importData() {
        return pokemonService.createPokemonDb()
    }

    @GetMapping("/pokemon")
    fun getAllPokemon(): ResponseEntity<List<Pokemon>> {
        return ResponseEntity(
            pokemonService.allPokemon(),
            HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("/pokemon/{id}")
    fun getPokemonById(
        @PathVariable id: Int
    ): ResponseEntity<PokemonResponse> {
        return ResponseEntity(
            pokemonService.getPokemonById(id),
            HttpStatus.NOT_FOUND
        )
    }

    //name?name=String
    @GetMapping("/pokemon/name")
    fun getPokemonByName(
        @RequestParam name: String
    ): Any {
        return ResponseEntity(
            pokemonService.getPokemonByName(name),
            HttpStatus.NOT_FOUND
        )
    }

    //page?pageNum=Int&pageSize=Int
    @GetMapping("/pokemon/page")
    fun getPokemonByPage(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "15") pageSize: Int
    ): ResponseEntity<Iterable<Pokemon>> {
        return ResponseEntity(
            pokemonService.getPokemonByPage(pageNum, pageSize),
            HttpStatus.NOT_FOUND
        )
    }
}
