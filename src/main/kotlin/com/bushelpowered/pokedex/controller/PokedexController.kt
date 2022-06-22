package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.Pokemon
import com.bushelpowered.pokedex.dataClasses.Trainer
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.web.bind.annotation.*


@RestController
class PokedexController(
    val pokemonService: PokemonService,
    val trainerService: TrainerService
) {

    @EventListener(ApplicationReadyEvent::class) // Import data on startup
    fun importData() {
        return pokemonService.createPokemonDb()
    }

    @GetMapping("/pokemon")
    fun getAllPokemon(): Iterable<Pokemon> {
        return pokemonService.allPokemon()
    }

    @GetMapping("/pokemon/{id}")
    fun getPokemonById(@PathVariable id: Int): Pokemon? {
        return pokemonService.getPokemon(id)
    }

    //page?pageNum=Int&pageSize=Int
    @GetMapping("/pokemon/page")
    fun getPokemonByPage(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "15") pageSize: Int
    ): Iterable<Pokemon> {
        return pokemonService.getPokemonByPage(pageNum, pageSize)
    }

    @GetMapping("/trainer/")
    fun getAllTrainers(): Iterable<Trainer> {
        return trainerService.getAllTrainers()
    }

    @GetMapping("/trainer/{id}")
    fun getTrainerById(@PathVariable id: Int): Trainer? {
        return trainerService.getTrainer(id)
    }

    @PostMapping("/trainer/")
    fun createEmployee(@RequestBody trainerInfo: Trainer) {
        trainerService.createTrainer(trainerInfo)
    }

    @PutMapping("/trainer/{id}")
    fun updateTrainerById(
        @PathVariable("id") trainerId: Int,
        @RequestBody trainerInfo: Trainer
    ) {
        trainerService.updateTrainerById(trainerId, trainerInfo)
    }

    @PutMapping("/trainer/{trainerId}/capturePokemon/{pokemonId}")
    fun capturePokemon(@PathVariable trainerId: Int, @PathVariable pokemonId: Int) {
        trainerService.capturePokemonToTrainer(trainerId, pokemonId)
    }

    @DeleteMapping("/trainer/{id}")
    fun deleteTrainerById(@PathVariable("id") trainerId: Int) {
        trainerService.deleteTrainer(trainerId)
    }
}
