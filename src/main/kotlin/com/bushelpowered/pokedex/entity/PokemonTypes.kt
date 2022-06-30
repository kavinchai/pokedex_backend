package com.bushelpowered.pokedex.entity

import javax.persistence.*

@Entity
@Table(name = "pokemon_types_table")
data class PokemonTypes(
    @Id
    @Column(name = "pokemon_types_id")
    val id: Int,

    @Column(name = "pokemon_id")
    val pokemonId: Int,

    @Column(name = "type_id")
    val typeId: Int?
)