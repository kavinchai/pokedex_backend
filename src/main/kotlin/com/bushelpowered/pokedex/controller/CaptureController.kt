package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.service.CaptureService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CaptureController(private val captureService: CaptureService) {
    @PutMapping("/capture")
    // Request body should be an "Object" Similar to a Response class, you should be able to use
    // a request class. Additionally, what if I request to capture pokemon # -1?
    fun capturePokemon(@RequestBody captureInfo: HashMap<String, Any>): Any {
        return if (
            captureInfo.containsKey("trainerId") &&
            captureInfo.containsKey("capturedPokemon")
        ) {
            ResponseEntity.ok(captureService.capturePokemonToTrainer(captureInfo))
        } else {
            ResponseEntity.notFound()
        }

    }
}