package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.request.RegisterTrainerRequest
import com.bushelpowered.pokedex.dto.request.UpdateTrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.dto.request.LoginRequest
import com.bushelpowered.pokedex.service.TrainerService
import com.bushelpowered.pokedex.utils.toLoginResponse
import com.bushelpowered.pokedex.utils.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
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
        } catch (e: ResponseStatusException) {
            ResponseEntity.badRequest().body("Register Error: ${e.reason}")
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body("Register Exception error: ${ex.message}")
        }
    }

    @PostMapping("/login")
    fun loginTrainerRequest(
        @RequestBody loginInfo: LoginRequest,
        response: HttpServletResponse
    ): ResponseEntity<String> {
        return try {
            ResponseEntity.ok(trainerService.loginTrainer(loginInfo, response))
        } catch (e: ResponseStatusException) {
            ResponseEntity.badRequest().body("Login Error: ${e.reason}")
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body("Login Exception error: Trainer does not exist")
        }
    }

    @GetMapping("/trainer")
    fun getTrainerPostLoginRequest(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        return try {
            val trainer = trainerService.getTrainerAfterLogin(jwt)
            ResponseEntity.ok(trainer.toLoginResponse())
        } catch (e: ResponseStatusException) {
            ResponseEntity.badRequest().body("Get Trainer Error: ${e.reason}")
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body("Get Trainer Exception error: ${ex.message}")
        }
    }

    @PostMapping("/logout")
    fun logoutTrainerRequest(
        @CookieValue("jwt") jwt: String?,
        response: HttpServletResponse
    ): ResponseEntity<String> {
        return try {
            ResponseEntity.ok(trainerService.logoutTrainer(jwt, response))
        } catch (e: ResponseStatusException) {
            ResponseEntity.badRequest().body("Logout Error: ${e.reason}")
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body("Logout Exception error: ${ex.message}")
        }
    }

    @PutMapping("/trainer")
    fun updateTrainerById(
        @RequestBody trainerRequest: UpdateTrainerRequest
    ): ResponseEntity<Any> {
        return try {
            val trainerModel = trainerService.updateTrainerById(trainerRequest)
            ResponseEntity.ok(
                trainerModel.toResponse()
            )
        } catch (e: ResponseStatusException) {
            ResponseEntity.badRequest().body("Update Error: ${e.reason}")
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body("Update Exception error: ${ex.message}")
        }

    }

    @DeleteMapping("/trainer")
    fun deleteTrainerById(
        @RequestBody deleteTrainerRequest: DeleteTrainerRequest
    ): ResponseEntity<Any> {
        return try {
            val trainerModel = trainerService.deleteTrainer(deleteTrainerRequest)
            ResponseEntity.ok(
                "Trainer ${trainerModel.id}: " +
                        "${trainerModel.firstname.replaceFirstChar { char -> char.uppercase() }} " +
                        "has been deleted"
            )
        } catch (e: ResponseStatusException) {
            ResponseEntity.badRequest().body("Delete Error: ${e.reason}")
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body("Delete Exception error: ${ex.message}")
        }

    }
}