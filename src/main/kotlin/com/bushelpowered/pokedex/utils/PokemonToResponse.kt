package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.PokemonResponse
import com.bushelpowered.pokedex.dto.PokemonStatResponse
import com.bushelpowered.pokedex.entity.Pokemon
import com.bushelpowered.pokedex.entity.PokemonStat

fun Pokemon.toPokemonResponse(): PokemonResponse {
    val typeResponseList = mutableListOf<String>()
    val abilityResponseList = mutableListOf<String>()
    val eggGroupResponseList = mutableListOf<String>()
    this.type.forEach {
        typeResponseList.add(it.type)
    }
    this.ability.forEach {
        abilityResponseList.add(it.ability)
    }
    this.eggGroup.forEach {
        eggGroupResponseList.add(it.eggGroup)
    }
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