package com.bushelpowered.pokedex.dto.request

import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

data class CreateTrainerRequest(
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String
){
    init{
        validate(this){
            validate(CreateTrainerRequest::username).isNotBlank()
            validate(CreateTrainerRequest::firstname).isNotBlank()
            validate(CreateTrainerRequest::lastname).isNotBlank()
            validate(CreateTrainerRequest::email).isNotBlank().isEmail()
        }
    }
}

