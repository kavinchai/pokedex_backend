package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.Trainer
import com.bushelpowered.pokedex.repository.TrainerRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class TrainerService (val db: TrainerRepository){
    fun getAllTrainers(): MutableIterable<Trainer> = db.findAll()

    fun getTrainer(id: Int): Optional<Trainer> {
        return db.findById(id)
    }

    fun createTrainer(trainerInfo: Trainer) = db.save(trainerInfo)

    fun updateTrainerById(id: Int, trainerInfo: Trainer){
        if (db.existsById(id)){
            db.save(
                Trainer(
                    id = trainerInfo.id,
                    userName = trainerInfo.userName,
                    firstName = trainerInfo.firstName,
                    lastName = trainerInfo.lastName,
                    emailId = trainerInfo.emailId
                )
            )
        }
        else{
            println("Error: Trainer does not exist")
        }
    }
}