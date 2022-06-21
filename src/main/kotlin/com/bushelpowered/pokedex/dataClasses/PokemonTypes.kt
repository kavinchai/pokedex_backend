package com.bushelpowered.pokedex.dataClasses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pokemon_types_table")
@JsonIgnoreProperties("pokemontypeid" )
data class PokemonTypes (
    @Id
    @Column(name = "pokemontypeid")
    @JsonProperty("pokemontypeid")
    val pokemonTypeId: Int,

    @Column(name = "type1")
    val type1: String?,

    @Column(name = "type2")
    val type2: String?
)