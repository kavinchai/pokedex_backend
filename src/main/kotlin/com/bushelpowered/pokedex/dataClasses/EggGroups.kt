package com.bushelpowered.pokedex.dataClasses

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "egg_groups_table")
data class EggGroups (
    @Id
    @Column(name = "egggroupid")
    val EggGroupID: Int,

    @Column(name = "egggroup1")
    val EggGroup1: String,

    @Column(name = "egggroup2")
    val EggGroup2: String

)