package com.bushelpowered.pokedex.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "egg_groups_table")
@JsonIgnoreProperties("egg_group_id")
data class EggGroups(
    @Id
    @Column(name = "egg_group_id")
    @JsonProperty("egg_group_id")
    val id: Int,

    @Column(name = "egggroup1")
    val eggGroup1: String?,

    @Column(name = "egggroup2")
    val eggGroup2: String?

)