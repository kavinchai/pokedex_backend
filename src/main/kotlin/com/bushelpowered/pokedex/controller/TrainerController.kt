package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dto.request.RegisterTrainerRequest
import com.bushelpowered.pokedex.dto.request.UpdateTrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.dto.request.LoginRequest
import com.bushelpowered.pokedex.dto.response.CrudTrainerResponse
import com.bushelpowered.pokedex.dto.response.LoginTrainerResponse
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
    ): ResponseEntity<CrudTrainerResponse> {
        val trainerModel = trainerService.registerTrainer(trainerRequest)
        return ResponseEntity(
            trainerModel.toResponse(),
             HttpStatus.CREATED
        )
    }

    @PostMapping("/login")
    fun login(
        @RequestBody loginInfo: LoginRequest,
        response: HttpServletResponse
    ): ResponseEntity<String> {

        val trainer = trainerService.getTrainerByEmail(loginInfo.email)

        //Check if login password matches trainer password
        if (!trainerService.comparePassword(loginInfo.password, trainer.password)){
            return ResponseEntity.badRequest().body("Error: Incorrect password")
        }

        // Create JWT
        val issuer = trainer.id.toString()
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS256, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true    // Front-end cannot see this cookie

        // Access HTTPServlet and set JWT as cookie
        response.addCookie(cookie)

        return ResponseEntity.ok("Successfully logged in")
    }

    @GetMapping("/trainer")
    fun trainer(@CookieValue("jwt") jwt: String?): ResponseEntity<Any>{
        try{
            if (jwt == null){   // If cookie does not contain JWT
                return ResponseEntity.status(401).body("Error: Not logged in")
            }

            // Get issuer id from claims
            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
            val trainer = trainerService.getTrainerById(body.issuer.toInt())
            return ResponseEntity.ok(trainer.toLoginResponse())
        } catch(e: ResponseStatusException){
            return ResponseEntity.status(401).body("Error: ${e.reason}")
        }
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<String> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0   // Set expiration to 0
        response.addCookie(cookie)  // Adds expired JWT cookie to HTTP servlet cookie

        return ResponseEntity.ok("Logged Out")
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