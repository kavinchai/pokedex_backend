package com.bushelpowered.pokedex.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "pokemon_stats_table")
@JsonIgnoreProperties("stat_id")
data class PokemonStat(
    @Id
    @Column(name = "stat_id")
    @JsonProperty("stat_id")
    val id: Int,

    @Column(name = "hp")
    val hp: Int,

    @Column(name = "speed")
    val speed: Int,

    @Column(name = "attack")
    val attack: Int,

    @Column(name = "defense")
    val defense: Int,

    @Column(name = "special_attack")
    val specialAttack: Int,

    @Column(name = "special_defense")
    val specialDefense: Int
)