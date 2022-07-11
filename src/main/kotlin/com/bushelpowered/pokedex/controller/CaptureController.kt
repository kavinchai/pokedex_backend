package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.CapturePokemonResponse
import com.bushelpowered.pokedex.service.CaptureService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CaptureController(private val captureService: CaptureService) {
    // return type should not be any.
    @PutMapping("/capture")
    fun capturePokemon(@RequestBody captureInfo: CapturePokemonResponse // type of "Response" should be "request"
    ): Any {
        // can this request fail?
        return ResponseEntity.ok(captureService.capturePokemonToTrainer(captureInfo))
    }
}