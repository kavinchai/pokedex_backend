package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.request.RegisterTrainerRequest
import com.bushelpowered.pokedex.dto.request.UpdateTrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.dto.request.LoginRequest
import com.bushelpowered.pokedex.model.Trainer
import com.bushelpowered.pokedex.repository.TrainerRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse


@Service
class TrainerService(private val trainerRepository: TrainerRepository) {

    fun registerTrainer(createTrainerRequest: RegisterTrainerRequest): Trainer {
        val trainerEmail = createTrainerRequest.email
        val trainerUsername = createTrainerRequest.username
        if (trainerRepository.existsByEmail(trainerEmail)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Email already exists"
            )
        }
        if (trainerRepository.existsByUsername(trainerUsername)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Username already exists"
            )
        }

        val passwordEncoder = BCryptPasswordEncoder()
        val encodePassword = passwordEncoder.encode(createTrainerRequest.password)

        trainerRepository.save(Trainer(
            id = 0,
            username = createTrainerRequest.username,
            firstname = createTrainerRequest.firstname,
            lastname = createTrainerRequest.lastname,
            email = createTrainerRequest.email,
            password = encodePassword,
            capturedPokemon = listOf()
        ))
        val trainerId = trainerRepository.findByUsername(createTrainerRequest.username).id
        return Trainer(
            id = trainerId,
            username = createTrainerRequest.username,
            firstname = createTrainerRequest.firstname,
            lastname = createTrainerRequest.lastname,
            email = createTrainerRequest.email,
            password = createTrainerRequest.password,
            capturedPokemon = listOf()
        )
    }

    fun loginTrainer(loginInfo: LoginRequest, response: HttpServletResponse): String {
        if (!trainerRepository.existsByEmail(loginInfo.email)){
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Trainer with that email does not exist"
            )
        }
        val trainer = trainerRepository.findByEmail(loginInfo.email)

        if (!comparePassword(loginInfo.password, trainer.password)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Incorrect password"
            )
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
        return "Successfully logged in"
    }

    fun getTrainerAfterLogin(jwt: String?): Trainer{
        if (jwt == null){   // If cookie does not contain JWT
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Not logged in"
            )
        }
        val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
        val trainerId = body.issuer.toInt()
        if (!trainerRepository.existsById(trainerId)){
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Trainer with that id does not exist"
            )
        }
        // Get issuer id from claims
        return trainerRepository.findById(trainerId).orElse(null)
    }

    fun logoutTrainer(response: HttpServletResponse): String {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0   // Set expiration to 0
        response.addCookie(cookie)  // Adds expired JWT cookie to HTTP servlet cookie
        return "Successfully logged out"
    }

    fun comparePassword(inputPassword: String, storedPassword: String): Boolean{
        return BCryptPasswordEncoder().matches(inputPassword, storedPassword)
    }

    fun updateTrainerById(updateTrainerRequest: UpdateTrainerRequest): Trainer {
        val trainerEmail = updateTrainerRequest.email
        val trainerUsername = updateTrainerRequest.username
        if (trainerRepository.existsByEmail(trainerEmail)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Email already exists"
            )
        }
        if (trainerRepository.existsByUsername(trainerUsername)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Username already exists"
            )
        }
        val trainer = trainerRepository.findById(updateTrainerRequest.id)
            .orElseThrow {
                ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Trainer does not exist"
                )
            }
        if (!comparePassword(updateTrainerRequest.password, trainer.password)){
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Incorrect password"
            )
        }
        trainerRepository.save(
            Trainer(
                id = updateTrainerRequest.id,
                username = updateTrainerRequest.username,
                firstname = updateTrainerRequest.firstname,
                lastname = updateTrainerRequest.lastname,
                email = updateTrainerRequest.email,
                password = trainer.password,
                capturedPokemon = trainer.capturedPokemon
            )
        )
        return Trainer(
            id = trainer.id,
            username = updateTrainerRequest.username,
            firstname = updateTrainerRequest.firstname,
            lastname = updateTrainerRequest.lastname,
            email = updateTrainerRequest.email,
            password = updateTrainerRequest.password,
            capturedPokemon = trainer.capturedPokemon
        )
    }

    fun deleteTrainer(deleteTrainerRequest: DeleteTrainerRequest): Trainer {
        val trainerId = deleteTrainerRequest.id
        val trainer = trainerRepository.findById(trainerId).orElseThrow {
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Trainer does not exist"
            )
        }
        trainerRepository.deleteById(trainerId)
        return Trainer(
            id = trainer.id,
            username = trainer.username,
            firstname = trainer.firstname,
            lastname = trainer.lastname,
            email = trainer.email,
            password = trainer.password,
            capturedPokemon = trainer.capturedPokemon
        )
    }
}

