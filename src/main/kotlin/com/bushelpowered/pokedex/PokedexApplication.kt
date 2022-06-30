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
    private val pokemonTypesRepository: PokemonTypeRepository,
    private val abilityRepository: AbilityRepository,
    private val pokemonAbilityRepository: PokemonAbilityRepository,
    private val eggGroupRepository: EggGroupRepository,
    private val genusRepository: GenusRepository,
    private val pokemonGenusRepository: PokemonGenusRepository,
    private val pokemonEggGroupRepository: PokemonEggGroupRepository
) {
    @EventListener(ApplicationReadyEvent::class) // Import data on startup
    fun importData() {
        PopulateData(
            typeRepository,
            pokemonTypesRepository,
            abilityRepository,
            pokemonAbilityRepository,
            eggGroupRepository,
            pokemonEggGroupRepository,
            genusRepository,
            pokemonGenusRepository,
            pokemonRepository,
        ).populateTables()
    }
}

fun main(args: Array<String>) {
    runApplication<PokedexApplication>(*args)
}
