package com.bushelpowered.pokedex.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/pokemon")
class PokedexController (val service: PokemonService){

    @GetMapping
    fun index() = service.allPokemon()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int) = service.findPokemon(id)

}
