package com.bushelpowered.pokedex.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "genus_table")
data class Genus(
    @Id
    @Column(name = "genus_id")
    val id: Int,

    @Column(name = "genus")
    val genus: String
)