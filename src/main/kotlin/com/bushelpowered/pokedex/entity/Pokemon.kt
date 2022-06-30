package com.bushelpowered.pokedex.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*


@Entity
@Table(name = "pokemon_table")
@JsonIgnoreProperties("pokemon_id")
data class Pokemon(
    @Id
    @Column(name = "pokemon_id")
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
    val type: List<Type>,

    @Column(name = "height")
    val height: Float,

    @Column(name = "weight")
    val weight: Float,

    @OneToMany
    @JoinTable(
        name = "pokemon_ability_table",
        joinColumns = [JoinColumn(name = "pokemon_id", referencedColumnName = "pokemon_id")],
        inverseJoinColumns = [JoinColumn(name = "ability_id", referencedColumnName = "ability_id")]
    )
    val ability: List<Ability>,

    @OneToMany
    @JoinTable(
        name = "pokemon_egg_group_table",
        joinColumns = [JoinColumn(name = "pokemon_id", referencedColumnName = "pokemon_id")],
        inverseJoinColumns = [JoinColumn(name = "egg_group_id", referencedColumnName = "egg_group_id")]
    )
    val eggGroup: List<EggGroup>,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemon_id", referencedColumnName = "stat_id")
    val stats: PokemonStat,

    @OneToMany
    @JoinTable(
        name = "pokemon_genus_table",
        joinColumns = [JoinColumn(name = "pokemon_id", referencedColumnName = "pokemon_id")],
        inverseJoinColumns = [JoinColumn(name = "genus_id", referencedColumnName = "genus_id")]
    )
    val genus: List<Genus>,

    @Column(name = "description")
    val description: String,
)


