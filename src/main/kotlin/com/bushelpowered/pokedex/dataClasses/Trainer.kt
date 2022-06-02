package com.bushelpowered.pokedex.dataClasses

import javax.persistence.*


@Entity
@Table(name = "trainer_table")
data class Trainer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "username", unique = true, nullable = false)
    val userName: String,

    @Column(name = "firstname", nullable = false)
    val firstName: String,

    @Column(name = "lastname", nullable = false)
    val lastName: String,

    @Column(name = "emailaddress", nullable = false)
    val emailId: String,

)


