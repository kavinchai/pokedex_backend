package com.bushelpowered.pokedex.dataClasses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pokemon_abilities_table")
@JsonIgnoreProperties("pokemonabilityid")
data class PokemonAbilities(
    @Id
    @Column(name = "pokemonabilityid")
    @JsonProperty("pokemonabilityid")
    val pokemonAbilityId: Int,

    @Column(name = "ability1")
    val ability1: String?,

    @Column(name = "ability2")
    val ability2: String?,

    @Column(name = "ability3")
    val ability3: String?,
)