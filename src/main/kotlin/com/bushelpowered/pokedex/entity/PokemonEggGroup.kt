package com.bushelpowered.pokedex.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="pokemon_egg_group_table")
data class PokemonEggGroup (
    @Id
    @Column(name="pokemon_egg_group_id")
    val id: Int,

    @Column(name="pokemon_id")
    val pokemonId: Int,

    @Column(name="egg_group_id")
    val abilityId: Int?
)