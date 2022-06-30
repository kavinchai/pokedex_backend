package com.bushelpowered.pokedex.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "types_table")
data class Types(
    @Id
    @Column(name = "type_id")
    val id: Int,

    @Column(name = "type")
    val type: String?,
)