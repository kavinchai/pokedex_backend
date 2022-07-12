package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.dto.request.CapturePokemonRequest
import com.bushelpowered.pokedex.model.CapturedPokemon
import com.bushelpowered.pokedex.service.CaptureService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.RejectedExecutionException

@SpringBootTest
class CaptureServiceTests {

    // Testing 1 successful capture
    @Test
    fun testValidCapture(){
        val trainerId = 1
        val pokemonId = 444
        val expectedResult = CapturedPokemon(
            id = 1444,
            trainer = trainerId,
            pokemon = pokemonId,
            timesCaught = 1
        )
        val capturePokemonRequest = CapturePokemonRequest(trainerId, pokemonId)
        val service = mockk<CaptureService>()
        every{ service.capturePokemonToTrainer(capturePokemonRequest)} returns expectedResult

        val actualResult = service.capturePokemonToTrainer(capturePokemonRequest)

        verify{service.capturePokemonToTrainer(capturePokemonRequest)}
        assertEquals(expectedResult, actualResult)

    }

    // Test invalid trainer ID
    @Test
    fun testInvalidTrainerId(){
        val trainerId = 999
        val pokemonId = 444
        val capturePokemonRequest = CapturePokemonRequest(trainerId, pokemonId)
        val service = mockk<CaptureService>()
        every{ service.capturePokemonToTrainer(capturePokemonRequest)} throws RejectedExecutionException("Error: Trainer does not exist")

        val ex = assertThrows<RejectedExecutionException> {
            service.capturePokemonToTrainer(capturePokemonRequest)
        }
        println(ex.message)
    }

    // Test invalid pokemon ID
    @Test
    fun testInvalidPokemonId(){
        val trainerId = 1
        val pokemonId = 999
        val capturePokemonRequest = CapturePokemonRequest(trainerId, pokemonId)
        val service = mockk<CaptureService>()
        every{ service.capturePokemonToTrainer(capturePokemonRequest)} throws RejectedExecutionException("Error: Pokemon does not exist")

        val ex = assertThrows<RejectedExecutionException> {
            service.capturePokemonToTrainer(capturePokemonRequest)
        }
        println(ex.message)
    }
}