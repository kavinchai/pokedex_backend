package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.Trainer
import com.bushelpowered.pokedex.repository.PokemonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
//@RequestMapping
class PokedexController (val pokemonService: PokemonService,
                         val trainerService: TrainerService){

    @GetMapping("/pokemon")
    fun getAllPokemon() = pokemonService.allPokemon()

    @GetMapping("/pokemon/{id}")
    fun getPokemonById(@PathVariable id: Int) = pokemonService.getPokemon(id)

    @GetMapping("/trainer")
    fun getAllTrainers() = trainerService.getAllTrainers()

    @GetMapping("/trainer/{id}")
    fun getTrainerById(@PathVariable id: Int) = trainerService.getTrainer(id)

    @PostMapping("/trainer")
    fun createEmployee(@RequestBody trainerInfo: Trainer) {
        trainerService.createTrainer(trainerInfo)
    }

    @PutMapping("/trainer/{id}")
    fun updateTrainerById(@PathVariable("id") trainerId: Int,
                          @RequestBody trainerInfo: Trainer) {
        trainerService.updateTrainerById(trainerId, trainerInfo)
    }

}
