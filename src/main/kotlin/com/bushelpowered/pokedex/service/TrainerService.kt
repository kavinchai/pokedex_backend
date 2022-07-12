package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.dto.request.RegisterTrainerRequest
import com.bushelpowered.pokedex.dto.request.UpdateTrainerRequest
import com.bushelpowered.pokedex.dto.request.DeleteTrainerRequest
import com.bushelpowered.pokedex.model.Trainer
import com.bushelpowered.pokedex.repository.TrainerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class TrainerService(private val trainerRepository: TrainerRepository) {
    fun getTrainerByEmail(email: String): Trainer {
        if (!trainerRepository.existsByEmail(email)){
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Trainer with that email does not exist"
            )
        }
        return trainerRepository.findByEmail(email)
    }

    fun getTrainerById(id: Int): Trainer{
        if (!trainerRepository.existsById(id)){
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Trainer with that id does not exist"
            )
        }
        return trainerRepository.findById(id).orElse(null)
    }

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

