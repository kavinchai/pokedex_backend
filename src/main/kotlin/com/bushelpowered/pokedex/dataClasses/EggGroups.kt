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
    val eggGroupId: Int,

    @Column(name = "egggroup1")
    val eggGroup1: String?,

    @Column(name = "egggroup2")
    val eggGroup2: String?

)