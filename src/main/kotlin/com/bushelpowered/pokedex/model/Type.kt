package com.bushelpowered.pokedex.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "type_table")
data class Type(
    @Id
    @Column(name = "type_id")
    val id: Int,

    @Column(name = "type")
    val type: String
)


