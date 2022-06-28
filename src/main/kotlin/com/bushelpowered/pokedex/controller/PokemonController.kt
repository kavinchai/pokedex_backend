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

    /*
    This should not live in a controller, the controllers should only
    be methods that directly map to an endpoint (in a 1:1 fashion)
     */
    @EventListener(ApplicationReadyEvent::class) // Import data on startup
    fun importData() {
        return pokemonService.createPokemonDb()
    }

    /*
        Here you are returning a response code of 404.
        We should return all pokemon with a status code of 200,
        or we should return no pokemon with a 404.
        The common syntax for a response entity is ResponseEntity.ok(data)
        or ResponseEntity.notFound("404 Message")
     */
    @GetMapping("/pokemon")
    fun getAllPokemon(): ResponseEntity<List<Pokemon>> {
        return ResponseEntity(
            pokemonService.allPokemon(),
            HttpStatus.NOT_FOUND
        )
    }

    /*
        Same as the comment above. We should return 200 if found, or else
        return not found.
     */
    @GetMapping("/pokemon/{id}")
    fun getPokemonById(
        @PathVariable id: Int
    ): ResponseEntity<PokemonResponse> {
        val pokemon = pokemonService.getPokemonById(id) ?: return ResponseEntity.notFound()

        return ResponseEntity.ok(
            pokemon.toResponse()
        )

        return ResponseEntity(
            pokemonService.getPokemonById(id),
            HttpStatus.NOT_FOUND
        )
    }

    /*
        In a "RESTful" API we would maintain the pattern of "/pokemon"
        and add a request parameter for "name". You can handle this in the
        above method, my checking if it is there, or you can add a method
        with the request parameter.

        You are also returning an "Any" when it should be "Pokemon"
     */
    @GetMapping("/pokemon")
    fun getPokemonByName(
        @RequestParam name: String
    ): Any {
        return ResponseEntity(
            pokemonService.getPokemonByName(name),
            HttpStatus.NOT_FOUND
        )
    }

    /*
        In a "RESTful" API we would maintain the pattern of "/pokemon"
        and add a request parameter for "page". You can handle this in the
        above method, my checking if it is there, or you can add a method
        with the request parameter.

        We want to return a "List" of Pokemon, not an "Iterable"
    */
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


    /*
        Example of "search" with the multi param, with a restful approach
        When using params, we always return a list. If we are querying for
        a single item, we always query by its identifier
     */

    @GetMapping("/pokemon")
    fun searchPokemon(
        @RequestParam(defaultValue = "0", name = "page") pageNum: Int,
        @RequestParam(defaultValue = "15", name="page_size") pageSize: Int,
        @RequestParam name: String?,
    ): ResponseEntity<List<Pokemon>> {
        // if name is not null...
        val pokemon = name?.let {
            // since this should search, we would ideally pass page size / limit in as well, and have the
            // service method return a list
            listOf(pokemonService.getPokemonByName(name))
        } ?: { // else
            pokemonService.getPokemonByPage(pageNum, pageSize).toList()
        }

        return ResponseEntity.ok(
            pokemon
        )
    }
}
