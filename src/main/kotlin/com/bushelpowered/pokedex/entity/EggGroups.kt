package com.bushelpowered.pokedex.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "egg_groups_table")
@JsonIgnoreProperties("egggroupid") // This is a "hack" so you can return this object through the API. We don't want that.
data class EggGroups(
    // we always use camel or snake case, everywhere
    // this should be @Column(name="egg_group_id") ideally, these are just "id"
    @Id
    @Column(name = "egggroupid")
    @JsonProperty("egggroupid")
    val eggGroupId: Int,

    // Egg groups should only be in the DB once ever. It looks like you join multiple in one column,
    // this should be a ManyToMany on Pokemon / Egg groups
    @Column(name = "egggroup1")
    val eggGroup1: String?,

    @Column(name = "egggroup2")
    val eggGroup2: String?

)