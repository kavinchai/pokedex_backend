package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.response.CrudTrainerResponse
import com.bushelpowered.pokedex.model.Trainer

fun Trainer.toResponse(): CrudTrainerResponse {
    return CrudTrainerResponse(
        id = this.id,
        username = this.username,
        firstname = this.firstname,
        lastname = this.lastname,
        email = this. email
    )
}