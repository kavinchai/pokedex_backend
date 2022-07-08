package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.PokemonStatResponse
import com.bushelpowered.pokedex.entity.PokemonStat

fun PokemonStat.toStatResponse(): PokemonStatResponse {

    return PokemonStatResponse(
        id = this.id,
        hp = this.hp,
        speed = this.speed,
        attack = this.attack,
        defense = this.defense,
        special_attack = this.specialAttack,
        special_defense = this.specialDefense
    )
}