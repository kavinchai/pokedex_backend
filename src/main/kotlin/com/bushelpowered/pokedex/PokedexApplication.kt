package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.repository.PokemonTypeRepository
import com.bushelpowered.pokedex.repository.TypeRepository
import com.bushelpowered.pokedex.service.ParseFile
import com.bushelpowered.pokedex.service.PokemonService
import com.bushelpowered.pokedex.service.PopulateData
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener

@SpringBootApplication
class PokedexApplication(
    private val pokemonRepository: PokemonRepository,
    private val typeRepository: TypeRepository,
    private val pokemonTypesRepository: PokemonTypeRepository
){
    @EventListener(ApplicationReadyEvent::class) // Import data on startup
    fun importData() {
        typeRepository.saveAll(PopulateData(typeRepository, pokemonTypesRepository).populateTypesTable())
        pokemonTypesRepository.saveAll(PopulateData(typeRepository, pokemonTypesRepository).populatePokemonTypesTable())
        pokemonRepository.saveAll(ParseFile(typeRepository, pokemonTypesRepository).listOfPokemon())

    }
}

fun main(args: Array<String>) {
    runApplication<PokedexApplication>(*args)
}
