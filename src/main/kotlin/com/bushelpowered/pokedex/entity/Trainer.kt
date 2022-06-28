package com.bushelpowered.pokedex.entity

import lombok.Data
import javax.persistence.*


@Entity
@Table(name = "trainer_table")
@Data
data class Trainer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainerid") // id
    val trainerId: Int,

    @Column(name = "username", unique = true, nullable = false)
    val userName: String,

    @Column(name = "firstname", nullable = false)
    val firstName: String,

    @Column(name = "lastname", nullable = false)
    val lastName: String,

    @Column(name = "emailaddress", nullable = false) // email is fine
    val emailId: String,

    @OneToMany
    @JoinTable(
        name = "captured_pokemon_table",
        joinColumns = [JoinColumn(name = "trainer", referencedColumnName = "trainerid")],
        inverseJoinColumns = [JoinColumn(name = "pokemon", referencedColumnName = "pokemonid")]
    )
    val capturedPokemon: List<Pokemon>?
)
