package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.Trainer
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.web.bind.annotation.*


@RestController
class PokedexController (val pokemonService: PokemonService,
                         val trainerService: TrainerService){

    @EventListener(ApplicationReadyEvent::class) // Import data on startup
    fun importData() = pokemonService.createPokemonDb()

    @GetMapping("/pokemon")
    fun getAllPokemon() = pokemonService.allPokemon()

    @GetMapping("/pokemon/{id}")
    fun getPokemonById(@PathVariable id: Int) = pokemonService.getPokemon(id)

    @GetMapping("pokemon/page")
    fun getPokemonByPage(@RequestParam(defaultValue = "0") pageNum: Int, @RequestParam(defaultValue = "15") pageSize: Int) = pokemonService.getPokemonByPage(pageNum, pageSize)

    @GetMapping("/trainer")
    fun getAllTrainers() = trainerService.getAllTrainers()

    @GetMapping("/trainer/{id}")
    fun getTrainerById(@PathVariable id: Int) = trainerService.getTrainer(id)

    @GetMapping("/trainer/{id}/capturedPokemon")
    fun getTrainerPokemonById(@PathVariable id: Int) = trainerService.getTrainerPokemon(id)

    @PostMapping("/trainer")
    fun createEmployee(@RequestBody trainerInfo: Trainer) {
        trainerService.createTrainer(trainerInfo)
    }

    @PutMapping("/trainer/{id}")
    fun updateTrainerById(@PathVariable("id") trainerId: Int,
                          @RequestBody trainerInfo: Trainer) {
        trainerService.updateTrainerById(trainerId, trainerInfo)
    }

    @DeleteMapping("/trainer/{id}")
    fun deleteTrainerById(@PathVariable("id") trainerId: Int) {
        trainerService.deleteTrainer(trainerId)
    }
}
