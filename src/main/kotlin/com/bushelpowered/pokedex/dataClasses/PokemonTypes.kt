package com.bushelpowered.pokedex.dataClasses

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pokemon_types_table")
data class PokemonTypes (
    @Id
    @Column(name = "pokemontypeid")
    val pokemonTypeId: Int,

    @Column(name = "type1")
    val type1: String?,

    @Column(name = "type2")
    val type2: String?
)