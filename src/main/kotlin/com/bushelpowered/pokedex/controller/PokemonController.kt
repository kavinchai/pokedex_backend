package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.*
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
        @RequestParam name: String?,
        @RequestParam id: Int?,
    ): ResponseEntity<out Any> {
        if (name != null && id == null) {    // name param provided
            return ResponseEntity.ok(
                listOf(
                    pokemonService.getPokemonByName(name)?.toResponse() ?: ResponseEntity.notFound()
                )
            )
        } else if (name == null && id != null) {   // id param provided
            return ResponseEntity.ok(
                pokemonService.getPokemonById(id)?.toResponse() ?: ResponseEntity.notFound()
            )
        }
        return ResponseEntity.ok(pokemonService.getPokemonByPage(pageNum, pageSize))
    }

    @GetMapping("/type")
    fun searchPokemonType(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "15") pageSize: Int,
        @RequestParam type: String?,
        @RequestParam type2: String?
    ): ResponseEntity<Any> {
        if (type != null) {
            return ResponseEntity.ok(
                pokemonService.getPokemonByType(type, type2, pageNum, pageSize)
            )
        }
        return ResponseEntity.ok(pokemonService.getPokemonByPage(pageNum, pageSize).toList())
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
        val typeResponseList = mutableListOf<TypeResponse>()
        val abilityResponseList = mutableListOf<AbilityResponse>()
        val eggGroupResponseList = mutableListOf<EggGroupResponse>()
        val genusResponseList = mutableListOf<GenusResponse>()
        this.type.forEach{
            typeResponseList.add(it.toTypeResponse())
        }
        this.ability.forEach{
            abilityResponseList.add(it.toAbilityResponse())
        }
        this.eggGroup.forEach{
            eggGroupResponseList.add(it.toEggGroupResponse())
        }
        this.genus.forEach{
            genusResponseList.add(it.toGenusResponse())
        }
        return PokemonResponse(
            id = this.id,
            name = this.name,
            type = typeResponseList,
            height = this.height,
            weight = this.weight,
            ability = abilityResponseList,
            eggGroup = eggGroupResponseList,
            stats = this.stats,
            genus = genusResponseList,
            description = this.description
        )
    }
}