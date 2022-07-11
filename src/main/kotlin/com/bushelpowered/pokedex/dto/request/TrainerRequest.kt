package com.bushelpowered.pokedex.dto.request

import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isPositive
import org.valiktor.validate

data class TrainerRequest(
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String
){
    init{
        validate(this){
            validate(TrainerRequest::id).isPositive()
            validate(TrainerRequest::username).isNotBlank()
            validate(TrainerRequest::firstname).isNotBlank()
            validate(TrainerRequest::lastname).isNotBlank()
            validate(TrainerRequest::email).isNotBlank().isEmail()
        }
    }
}
