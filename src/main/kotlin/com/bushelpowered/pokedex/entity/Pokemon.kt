package com.bushelpowered.pokedex.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*


@Entity
@Table(name = "pokemon_table") // All tables need to be named with _ notation
@JsonIgnoreProperties("pokemon_id")
data class Pokemon(
    @Id
    @Column(name = "pokemon_id") // All names in @Column need to be lowercase
    @JsonProperty("pokemon_id")
    val id: Int,

    @Column(name = "name")
    val name: String,

    @OneToMany
    @JoinTable(
        name = "pokemon_type_table",
        joinColumns = [JoinColumn(name = "pokemon_id", referencedColumnName = "pokemon_id")],
        inverseJoinColumns = [JoinColumn(name = "type_id", referencedColumnName = "type_id")]
    )
    val pokemonTypes: List<Type>,

    @Column(name = "height")
    val height: Double,

    @Column(name = "weight")
    val weight: Double,

    @OneToMany
    @JoinTable(
        name = "pokemon_ability_table",
        joinColumns = [JoinColumn(name = "pokemon_id", referencedColumnName = "pokemon_id")],
        inverseJoinColumns = [JoinColumn(name = "ability_id", referencedColumnName = "ability_id")]
    )
    val pokemonAbilities: List<Ability>,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemon_id", referencedColumnName = "egg_group_id")
    val eggGroup: EggGroup,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemon_id", referencedColumnName = "stat_id")
    val pokemonStats: PokemonStats,

    @Column(name = "genus")
    val genus: String,

    @Column(name = "description")
    val description: String,
)


