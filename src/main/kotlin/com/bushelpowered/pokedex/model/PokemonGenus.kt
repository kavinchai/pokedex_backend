package com.bushelpowered.pokedex.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="pokemon_genus_table")
data class PokemonGenus (
    @Id
    @Column(name="pokemon_genus_id")
    val id: Int,

    @Column(name="pokemon_id")
    val pokemonId: Int,

    @Column(name="genus_id")
    val genusId: Int
)

