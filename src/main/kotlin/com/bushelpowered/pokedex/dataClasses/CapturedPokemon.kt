package com.bushelpowered.pokedex.dataClasses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import javax.persistence.*


@Entity
@Table(name = "captured_pokemon_table") // All tables need to be named with _ notation
@JsonIgnoreProperties("capturedpokemonid")
data class CapturedPokemon(
    @Id
    @Column(name = "capturedpokemonid") // All names in @Column need to be lowercase
    @JsonProperty("capturedpokemonid")
    val trainerPokemonId: Int,

    @Column(name = "trainer")
    val trainer: Int,

    @Column(name = "pokemon")
    val pokemon: Int
)
