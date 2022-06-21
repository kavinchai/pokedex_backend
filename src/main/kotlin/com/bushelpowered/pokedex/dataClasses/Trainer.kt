package com.bushelpowered.pokedex.dataClasses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*


@Entity
@Table(name = "trainer_table")
data class Trainer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainerid")
    val trainerId: Int,

    @Column(name = "username", unique = true, nullable = false)
    val userName: String,

    @Column(name = "firstname", nullable = false)
    val firstName: String,

    @Column(name = "lastname", nullable = false)
    val lastName: String,

    @Column(name = "emailaddress", nullable = false)
    val emailId: String,

    @OneToMany
    @JoinTable(name = "captured_pokemon_table",
        joinColumns = [JoinColumn(name = "trainer", referencedColumnName = "trainerid")],
        inverseJoinColumns = [JoinColumn(name = "pokemon", referencedColumnName = "pokemonid")])
    var capturedPokemon: List<Pokemon>?
)


