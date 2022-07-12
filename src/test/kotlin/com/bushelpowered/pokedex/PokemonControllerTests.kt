package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.controller.PokemonController
import com.bushelpowered.pokedex.model.*
import com.bushelpowered.pokedex.service.PokemonService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(SpringExtension::class)
@WebMvcTest(PokemonController::class)
class PokemonControllerTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var pokemonservice : PokemonService

    @Test
    fun getPokemonName(){
        val pokemonName = "pikachu"
        val expectedResult = Pokemon(
            id=25,
            name="Pikachu",
            type= listOf(Type(id=8, type="electric")),
            height=4.0,
            weight=60.0,
            ability=listOf(
                Ability(id=21, ability="lightning-rod"),
                Ability(id=22, ability="static")
            ),
            eggGroup=listOf(
                EggGroup(id=8, eggGroup="fairy"),
                EggGroup(id=7, eggGroup="ground")
            ),
            stats= PokemonStat(
                id=25, hp=35, speed=90, attack=55, defense=40,
                specialAttack=50, specialDefense=50
            ),
            genus=listOf(Genus(id=14, genus="Mouse Pokemon")),
            description="It’ s in its nature to store electricity. It feels" +
            "stressed now and then if it’s unable to fully " +
            "discharge the electricity.")
        val service = mockk<PokemonService>()
        every{service.getPokemonByName(pokemonName)} returns expectedResult

//        val result = mockMvc.perform(get"/pokemon?name=pikachu")
    }
}