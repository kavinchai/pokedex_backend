package com.bushelpowered.pokedex.dto.request

import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

data class RegisterTrainerRequest(
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
){
    init{
        validate(this){
            validate(RegisterTrainerRequest::username).isNotBlank()
            validate(RegisterTrainerRequest::firstname).isNotBlank()
            validate(RegisterTrainerRequest::lastname).isNotBlank()
            validate(RegisterTrainerRequest::email).isNotBlank().isEmail()
            validate(RegisterTrainerRequest::password).isNotBlank()
        }
    }
}

