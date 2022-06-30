package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.PokemonResponse
import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.repository.TypeRepository
import com.bushelpowered.pokedex.service.PokemonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PokemonController(
    private val pokemonService: PokemonService,
    private val typeRepository: TypeRepository
) {
    @GetMapping("/pokemon")
    fun searchPokemon(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "15") pageSize: Int,
        @RequestParam name:String?,
    ): ResponseEntity<Any> {
        val pokemon = if (name == null){
            pokemonService.getPokemonByPage(pageNum, pageSize).toList()
        } else{
            listOf(pokemonService.getPokemonByName(name))
        }
        return ResponseEntity.ok(pokemon)
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
//            pokemonTypes = this.pokemonTypes,
            height = this.height,
            weight = this.weight,
//            pokemonAbilities = this.ability,
//            eggGroup = this.eggGroup,
            pokemonStats = this.pokemonStats,
            genus = this.genus,
            description = this.description
        )
    }
}
