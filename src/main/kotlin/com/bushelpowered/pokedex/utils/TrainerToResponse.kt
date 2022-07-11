package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.response.TrainerResponse
import com.bushelpowered.pokedex.entity.Trainer

fun Trainer.toResponse(): TrainerResponse {
    return TrainerResponse(
        id = this.id,
        username = this.username,
        firstname = this.firstname,
        lastname = this.lastname,
        email = this. email
    )
}