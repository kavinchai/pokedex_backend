package com.bushelpowered.pokedex.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "pokemon_type_table")
data class PokemonType(
    @Id
    @Column(name = "pokemon_type_id")
    val id: Int,

    @Column(name = "pokemon_id")
    val pokemonId: Int,

    @Column(name = "type_id")
    val typeId: Int
)