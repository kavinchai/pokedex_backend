package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.model.*
import com.bushelpowered.pokedex.service.PokemonService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PokemonServiceTests {
    @Test
    fun testGetPokemonById(){
        val expectedResult = Pokemon(
            id = 15,
            name = "Beedrill",
            type = listOf(Type(id = 1, type = "poison"), Type(id = 6, type = "bug")),
            height = 10.0,
            weight = 295.0,
            ability = listOf(
                Ability(id = 12, ability = "sniper"),
                Ability(id = 13, ability = "swarm")
            ),
            eggGroup = listOf(EggGroup(id = 5, eggGroup = "bug")),
            stats = PokemonStat(
                id = 15,
                hp = 65,
                speed = 75,
                attack = 90,
                defense = 40,
                specialAttack = 45,
                specialDefense = 80
            ),
            genus = listOf(Genus(id = 11, genus = "Poison Bee Pokemon")),
            description = "Beedrill is extremely territorial. " +
                    "No one should ever approach its nest—this is for their own safety. " +
                    "If angered, they will attack in a furious swarm."
        )
        val service = mockk<PokemonService>()
        every { service.getPokemonById(15) } returns expectedResult

        val actualResult = service.getPokemonById(15)

        verify{service.getPokemonById(15)}
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun testGetPokemonByName(){
        val expectedResult = Pokemon(
            id = 3,
            name = "Venusaur",
            type = listOf(Type(id=1, type="poison"), Type(id=2, type="grass")),
            height = 20.0,
            weight = 1000.0,
            ability = listOf(
                Ability(id=1, ability="chlorophyll"),
                Ability(id=2, ability="overgrow")
            ),
            eggGroup = listOf(
                EggGroup(id=1, eggGroup="plant"),
                EggGroup(id=2, eggGroup="monster"))
            ,
            stats = PokemonStat(
                id = 3,
                hp=80,
                speed=80,
                attack=82,
                defense=83,
                specialAttack=100,
                specialDefense=100
            ),
            genus = listOf(Genus(id=1, genus="Seed Pokemon")),
            description = "There is a large flower on Venusaur’s back. The flower is said\n" +
                    "    to take on vivid colors if it gets plenty of nutrition and sunlight.\n" +
                    "    The flower’s aroma soothes the emotions of people."
        )
        val service = mockk<PokemonService>()
        every { service.getPokemonByName("venusaur") } returns expectedResult

        val actualResult = service.getPokemonByName("venusaur")

        verify{service.getPokemonByName("venusaur")}
        assertEquals(expectedResult, actualResult)
    }

}
