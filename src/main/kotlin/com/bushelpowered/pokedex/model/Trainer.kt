package com.bushelpowered.pokedex.model

import lombok.Data
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*


@Entity
@Table(name = "trainer_table")
@Data
data class Trainer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    val id: Int,

    @Column(name = "username", unique = true, nullable = false)
    val username: String,

    @Column(name = "firstname", nullable = false)
    val firstname: String,

    @Column(name = "lastname", nullable = false)
    val lastname: String,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column
    val password: String,

    @OneToMany
    @JoinTable(
        name = "captured_pokemon_table",
        joinColumns = [JoinColumn(name = "trainer", referencedColumnName = "trainer_id")],
        inverseJoinColumns = [JoinColumn(name = "pokemon", referencedColumnName = "pokemon_id")]
    )
    val capturedPokemon: List<Pokemon>,


)


