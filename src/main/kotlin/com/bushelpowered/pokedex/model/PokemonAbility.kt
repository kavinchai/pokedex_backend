package com.bushelpowered.pokedex.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="pokemon_ability_table")
data class PokemonAbility (
    @Id
    @Column(name="pokemon_ability_id")
    val id: Int,

    @Column(name="pokemon_id")
    val pokemonId: Int,

    @Column(name="ability_id")
    val abilityId: Int
)