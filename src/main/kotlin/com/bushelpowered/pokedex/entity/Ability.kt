package com.bushelpowered.pokedex.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ability_table")
data class Ability(
    @Id
    @Column(name = "ability_id")
    val id: Int,

    @Column(name = "ability")
    val ability: String,
)