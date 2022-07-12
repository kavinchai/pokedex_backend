package com.bushelpowered.pokedex.dto.request

import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isPositive
import org.valiktor.validate

data class UpdateTrainerRequest(
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
){
    init{
        validate(this){
            validate(UpdateTrainerRequest::id).isPositive()
            validate(UpdateTrainerRequest::username).isNotBlank()
            validate(UpdateTrainerRequest::firstname).isNotBlank()
            validate(UpdateTrainerRequest::lastname).isNotBlank()
            validate(UpdateTrainerRequest::email).isNotBlank().isEmail()
            validate(UpdateTrainerRequest::password).isNotBlank()
        }
    }
}
