package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.controller.PokemonController
import com.bushelpowered.pokedex.service.PokemonService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest(PokemonController::class)
class PokemonControllerTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var pokemonservice : PokemonService

    @Test
    fun getPokemonName(){

    }
}