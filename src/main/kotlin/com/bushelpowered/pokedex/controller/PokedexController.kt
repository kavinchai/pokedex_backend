package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.PokemonResponse
import com.bushelpowered.pokedex.dto.TrainerResponse
import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.entity.Trainer
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class PokedexController(
    private val pokemonService: PokemonService,
    private val trainerService: TrainerService,
) {
    @Autowired
    private val modelMapper: ModelMapper? = null

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

    @GetMapping("/trainer/")
    fun getAllTrainers(): ResponseEntity<List<Trainer>> {
        return ResponseEntity(
            trainerService.getAllTrainers(),
            HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("/trainer/{id}")
    fun getTrainerById(
        @PathVariable id: Int
    ): ResponseEntity<TrainerResponse> {
        val trainerResponse: TrainerResponse? = trainerService.getTrainer(id)
        return ResponseEntity(
            trainerResponse,
            HttpStatus.NOT_FOUND
        )
    }

    @PostMapping("/trainer/")
    fun createEmployee(
        @RequestBody trainerInfo: Trainer
    ): ResponseEntity<Unit> {
        return ResponseEntity(
            trainerService.createTrainer(trainerInfo),
            HttpStatus.CREATED
        )
    }

    @PutMapping("/trainer/{id}")
    fun updateTrainerById(
        @PathVariable("id") trainerId: Int,
        @RequestBody trainerInfo: Trainer
    ): ResponseEntity<Unit> {
        return ResponseEntity(
            trainerService.updateTrainerById(trainerId, trainerInfo),
            HttpStatus.OK
        )
    }

    @PutMapping("/trainer/{trainerId}/capturePokemon/{pokemonId}")
    fun capturePokemon(
        @PathVariable trainerId: Int,
        @PathVariable pokemonId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity(
            trainerService.capturePokemonToTrainer(trainerId, pokemonId),
            HttpStatus.OK
        )
    }

    @DeleteMapping("/trainer/{id}")
    fun deleteTrainerById(
        @PathVariable("id") trainerId: Int
    ): ResponseEntity<Unit> {
        return ResponseEntity(
            trainerService.deleteTrainer(trainerId),
            HttpStatus.OK
        )
    }
}
