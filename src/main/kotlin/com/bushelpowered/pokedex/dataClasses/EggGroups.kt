package com.bushelpowered.pokedex.dataClasses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "egg_groups_table")
@JsonIgnoreProperties("egggroupid" )
data class EggGroups (
    @Id
    @Column(name = "egggroupid")
    @JsonProperty("egggroupid")
    val eggGroupId: Int,

    @Column(name = "egggroup1")
    val eggGroup1: String?,

    @Column(name = "egggroup2")
    val eggGroup2: String?

)