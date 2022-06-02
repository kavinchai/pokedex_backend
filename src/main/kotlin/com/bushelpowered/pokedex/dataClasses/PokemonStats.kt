package com.bushelpowered.pokedex.dataClasses

import javax.persistence.*

@Entity
@Table(name = "pokemon_stats_table")
data class PokemonStats (
    @Id
    @Column(name = "statid")
    val statID: Int,

    @Column(name = "hp")
    val hp: Int,

    @Column(name = "speed")
    val speed: Int,

    @Column(name = "attack")
    val attack: Int,

    @Column(name = "defense")
    val defense: Int,

    @Column(name = "specialattack")
    val specialAttack: Int,

    @Column(name = "specialdefense")
    val specialDefense: Int,

)