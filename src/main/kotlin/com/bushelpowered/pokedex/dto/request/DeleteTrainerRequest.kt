package com.bushelpowered.pokedex.dto.request

import org.valiktor.functions.isPositive
import org.valiktor.validate

data class DeleteTrainerRequest(
    val id: Int
){
    init{
        validate(this){
            validate(DeleteTrainerRequest::id).isPositive()
        }
    }
}
