package com.bushelpowered.pokedex.entity

import javax.persistence.*

@Entity
@Table(name = "captured_pokemon_table") // All tables need to be named with _ notation
data class CapturedPokemon(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "trainer")
    val trainer: Int,

    @Column(name = "pokemon")
    val pokemon: Int,

    @Column(name = "times_caught")
    val timesCaught: Int
)
