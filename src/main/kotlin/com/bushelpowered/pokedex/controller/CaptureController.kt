package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.request.CapturePokemonRequest
import com.bushelpowered.pokedex.dto.response.CapturePokemonResponse
import com.bushelpowered.pokedex.service.CaptureService
import com.bushelpowered.pokedex.utils.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class CaptureController(private val captureService: CaptureService) {
    @PutMapping("/capture")
    fun capturePokemon(
        @RequestBody captureRequest: CapturePokemonRequest
    ): ResponseEntity<Any> {
        return try {
            val trainerCapturedInfo = captureService.capturePokemonToTrainer(captureRequest)
            ResponseEntity.ok(trainerCapturedInfo.toResponse())
        } catch (e: ResponseStatusException) {
            ResponseEntity.badRequest().body("Error: ${e.reason}")
        }
    }
}