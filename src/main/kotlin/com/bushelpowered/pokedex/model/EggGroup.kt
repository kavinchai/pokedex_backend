package com.bushelpowered.pokedex.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "egg_group_table")
data class EggGroup(
    @Id
    @Column(name = "egg_group_id")
    val id: Int,

    @Column(name = "egg_group")
    val eggGroup: String
)