package com.bushelpowered.pokedex.dataClasses

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pokemon_abilities_table")
data class PokemonAbilities (
    @Id
    @Column(name = "pokemonabilityid")
    val PokemonAbilityID: Int,

    @Column(name = "ability1")
    val Ability1: String,

    @Column(name = "ability2")
    val Ability2: String
)