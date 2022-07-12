package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.request.RegisterTrainerRequest
import com.bushelpowered.pokedex.dto.request.UpdateTrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.dto.request.LoginRequest
import com.bushelpowered.pokedex.dto.response.CrudTrainerResponse
import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.utils.toLoginResponse
import com.bushelpowered.pokedex.utils.toResponse
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/trainer")
    fun registerTrainer(
        @RequestBody trainerRequest: RegisterTrainerRequest
    ): ResponseEntity<Any> {
        return try {
            val trainerModel = trainerService.registerTrainer(trainerRequest)
            ResponseEntity(
                trainerModel.toResponse(),
                HttpStatus.CREATED
            )
        } catch (e: ResponseStatusException){
            ResponseEntity.badRequest().body("Error: ${e.reason}")
        }
    }

    @PostMapping("/login")
    fun loginTrainerRequest(
        @RequestBody loginInfo: LoginRequest,
        response: HttpServletResponse
    ): ResponseEntity<String> {
        return try{
            ResponseEntity.ok(trainerService.loginTrainer(loginInfo, response))
        } catch (e: ResponseStatusException){
            ResponseEntity.badRequest().body("Error: ${e.reason}")
        }
    }

    @GetMapping("/trainer")
    fun getTrainerPostLoginRequest(@CookieValue("jwt") jwt: String?): ResponseEntity<Any>{
        return try{
            val trainer = trainerService.getTrainerAfterLogin(jwt)
            ResponseEntity.ok(trainer.toLoginResponse())
        } catch(e: ResponseStatusException){
            ResponseEntity.badRequest().body("Error: ${e.reason}")
        }
    }

    @PostMapping("/logout")
    fun logoutTrainerRequest(response: HttpServletResponse): ResponseEntity<String> {
        return ResponseEntity.ok(trainerService.logoutTrainer(response))
    }

    @PutMapping("/trainer")
    fun updateTrainerById(
        @RequestBody trainerRequest: UpdateTrainerRequest
    ): ResponseEntity<Any> {
        return try{
            val trainerModel = trainerService.updateTrainerById(trainerRequest)
            ResponseEntity.ok(
                trainerModel.toResponse()
            )
        } catch (e: ResponseStatusException){
            ResponseEntity.badRequest().body("Error: ${e.reason}")
        }

    }

    @DeleteMapping("/trainer")
    fun deleteTrainerById(
        @RequestBody deleteTrainerRequest: DeleteTrainerRequest
    ): ResponseEntity<Any> {
        return try{
            val trainerModel = trainerService.deleteTrainer(deleteTrainerRequest)
            ResponseEntity.ok(
                "Trainer ${trainerModel.id}: ${trainerModel.firstname} has been deleted"
            )
        } catch (e: ResponseStatusException){
            ResponseEntity.badRequest().body("Error: ${e.reason}")
        }

    }
}