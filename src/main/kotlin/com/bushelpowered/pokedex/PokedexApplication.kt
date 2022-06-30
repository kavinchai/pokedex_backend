package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.repository.*
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
    private val abilityRepository: AbilityRepository,
    private val pokemonAbilityRepository: PokemonAbilityRepository,
    private val pokemonTypesRepository: PokemonTypeRepository
) {
    @EventListener(ApplicationReadyEvent::class) // Import data on startup
    fun importData() {
        typeRepository.saveAll(PopulateData(typeRepository, pokemonTypesRepository, abilityRepository, pokemonAbilityRepository).populateTypesTable())
        pokemonTypesRepository.saveAll(PopulateData(typeRepository, pokemonTypesRepository, abilityRepository, pokemonAbilityRepository).populatePokemonTypesTable())
        abilityRepository.saveAll(PopulateData(typeRepository, pokemonTypesRepository, abilityRepository, pokemonAbilityRepository).populateAbilityTable())
        pokemonAbilityRepository.saveAll(PopulateData(typeRepository, pokemonTypesRepository, abilityRepository, pokemonAbilityRepository).populatePokemonAbilityTable())

//        PopulateData(typeRepository, pokemonTypesRepository, abilityRepository, pokemonAbilityRepository).listOfPokemon()
        pokemonRepository.saveAll(PopulateData(typeRepository, pokemonTypesRepository, abilityRepository, pokemonAbilityRepository).listOfPokemon())
    }
}

fun main(args: Array<String>) {
    runApplication<PokedexApplication>(*args)
}
