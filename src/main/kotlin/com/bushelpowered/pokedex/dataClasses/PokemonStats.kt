package com.bushelpowered.pokedex.dataClasses

import javax.persistence.*

@Entity
@Table(name = "pokemon_stats_table")
data class PokemonStats (
    @Id
    @Column(name = "statid")
    val StatID: Int,

    @Column(name = "hp")
    val Hp: Int,

    @Column(name = "speed")
    val Speed: Int,

    @Column(name = "attack")
    val Attack: Int,

    @Column(name = "defense")
    val Defense: Int,

    @Column(name = "specialattack")
    val SpecialAttack: Int,

    @Column(name = "specialdefense")
    val SpecialDefense: Int,

)