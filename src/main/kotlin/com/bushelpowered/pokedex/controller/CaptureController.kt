package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.CapturePokemonRequest
import com.bushelpowered.pokedex.dto.CapturePokemonResponse
import com.bushelpowered.pokedex.service.CaptureService
import com.bushelpowered.pokedex.utils.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.isIn
import org.valiktor.functions.isPositive
import org.valiktor.i18n.mapToMessage
import org.valiktor.validate
import java.util.*

@RestController
class CaptureController(private val captureService: CaptureService) {
    @PutMapping("/capture")
    fun capturePokemon(@RequestBody captureInfo: CapturePokemonRequest): ResponseEntity<CapturePokemonResponse> {
        try{
            validate(captureInfo){
                validate(CapturePokemonRequest::trainerId).isPositive()
                validate(CapturePokemonRequest::pokemonId).isPositive()
            }
        } catch (e: ConstraintViolationException){
            e.constraintViolations
                .mapToMessage(baseName = "messages", locale = Locale.ENGLISH)
                .map{"Error ${it.property}: ${it.message}"}
                .forEach(::println)
        }
        val trainerCapturedInfo = captureService.capturePokemonToTrainer(captureInfo)
        return ResponseEntity.ok(trainerCapturedInfo.toResponse())
    }
}