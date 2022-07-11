package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.CapturePokemonRequest
import com.bushelpowered.pokedex.service.CaptureService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CaptureController(private val captureService: CaptureService) {
    @PutMapping("/capture")
    fun capturePokemon(@RequestBody captureInfo: CapturePokemonRequest): Any {
        return ResponseEntity.ok(captureService.capturePokemonToTrainer(captureInfo))
    }
}