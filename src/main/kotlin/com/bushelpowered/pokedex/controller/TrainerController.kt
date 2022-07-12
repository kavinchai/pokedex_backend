package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.request.RegisterTrainerRequest
import com.bushelpowered.pokedex.dto.request.UpdateTrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.dto.request.LoginRequest
import com.bushelpowered.pokedex.dto.response.CrudTrainerResponse
import com.bushelpowered.pokedex.dto.response.LoginTrainerResponse
import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.utils.toResponse
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/trainer")
    fun registerTrainer(
        @RequestBody trainerRequest: RegisterTrainerRequest
    ): ResponseEntity<CrudTrainerResponse> {
        val trainerModel = trainerService.registerTrainer(trainerRequest)
        return ResponseEntity(
            trainerModel.toResponse(),
             HttpStatus.CREATED
        )
    }

    @PostMapping("/login")
    fun login(@RequestBody loginInfo: LoginRequest, response: HttpServletResponse): ResponseEntity<Any> {
        val trainer = trainerService.getTrainer(loginInfo.email)
        if (!trainerService.comparePassword(loginInfo.password, trainer.password)){
            return ResponseEntity.badRequest().body("Error: Incorrect password")
        }

        val issuer = trainer.id.toString()
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS256, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(
            LoginTrainerResponse(
                id = trainer.id,
                username = trainer.username,
                firstname = trainer.firstname,
                lastname = trainer.lastname,
                email = trainer.email,
                capturedPokemon = trainer.capturedPokemon
            )
        )
    }

    @PutMapping("/trainer")
    fun updateTrainerById(
        @RequestBody trainerRequest: UpdateTrainerRequest
    ): ResponseEntity<CrudTrainerResponse> {
        val trainerModel = trainerService.updateTrainerById(trainerRequest)
        return ResponseEntity.ok(
            trainerModel.toResponse()
        )
    }

    @DeleteMapping("/trainer")
    fun deleteTrainerById(
        @RequestBody deleteTrainerRequest: DeleteTrainerRequest
    ): ResponseEntity<CrudTrainerResponse> {
        val trainerModel = trainerService.deleteTrainer(deleteTrainerRequest)
        return ResponseEntity.ok(
            trainerModel.toResponse()
        )
    }
}