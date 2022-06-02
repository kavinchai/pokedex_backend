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
    val PokemonTypeID: Int,

    @Column(name = "type1")
    val Type1: String?,

    @Column(name = "type2")
    val Type2: String?
)