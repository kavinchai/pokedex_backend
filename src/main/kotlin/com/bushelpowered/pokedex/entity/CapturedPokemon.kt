package com.bushelpowered.pokedex.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*


@Entity
@Table(name = "captured_pokemon_table") // All tables need to be named with _ notation
@JsonIgnoreProperties("captured_pokemon_id")
data class CapturedPokemon(
    @Id
    @Column(name = "captured_pokemon_id")
    @JsonProperty("captured_pokemon_id")
    val id: Int,

    @Column(name = "trainer")
    val trainer: Int,

    @Column(name = "pokemon")
    val pokemon: Int
)
