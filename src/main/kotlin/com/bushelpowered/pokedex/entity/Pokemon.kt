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

//    @OneToOne(cascade = [CascadeType.ALL])
//    @JoinColumn(name = "pokemon_id", referencedColumnName = "pokemon_type_id")
//    val pokemonTypes: PokemonTypes,

    @OneToMany
    @JoinTable(
        name = "pokemon_types_table",
        joinColumns = [JoinColumn(name = "pokemon_id", referencedColumnName = "pokemon_id")],
        inverseJoinColumns = [JoinColumn(name = "type_id", referencedColumnName = "type_id")]
    )
    val pokemonTypes: List<Types>,

    @Column(name = "height")
    val height: Double,

    @Column(name = "weight")
    val weight: Double,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemon_id", referencedColumnName = "pokemon_ability_id")
    val pokemonAbilities: PokemonAbilities,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemon_id", referencedColumnName = "egg_group_id")
    val eggGroups: EggGroups,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemon_id", referencedColumnName = "stat_id")
    val pokemonStats: PokemonStats,

    @Column(name = "genus")
    val genus: String,

    @Column(name = "description")
    val description: String,
)


