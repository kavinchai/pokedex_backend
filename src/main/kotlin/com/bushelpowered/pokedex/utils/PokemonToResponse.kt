package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.response.PokemonResponse
import com.bushelpowered.pokedex.dto.response.PokemonStatResponse
import com.bushelpowered.pokedex.model.Pokemon

fun Pokemon.toPokemonResponse(): PokemonResponse {
    val typeResponseList = this.type.map{it.type}
    val abilityResponseList = this.ability.map{it.ability}
    val eggGroupResponseList = this.eggGroup.map{it.eggGroup}

    val statResponse = PokemonStatResponse(
        id = this.stats.id,
        hp = this.stats.hp,
        speed = this.stats.speed,
        attack = this.stats.attack,
        defense = this.stats.defense,
        specialAttack = this.stats.specialAttack,
        specialDefense = this.stats.specialDefense
    )
    return PokemonResponse(
        id = this.id,
        name = this.name,
        type = typeResponseList,
        height = this.height,
        weight = this.weight,
        ability = abilityResponseList,
        eggGroup = eggGroupResponseList,
        stats = statResponse,
        genus = this.genus[0].genus,
        description = this.description
    )
}