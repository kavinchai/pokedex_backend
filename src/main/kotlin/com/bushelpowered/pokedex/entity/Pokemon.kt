package com.bushelpowered.pokedex.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*


@Entity
@Table(name = "pokemon_table") // All tables need to be named with _ notation
@JsonIgnoreProperties("pokemonid")
data class Pokemon(
    @Id
    @Column(name = "pokemonid") // All names in @Column need to be lowercase
    @JsonProperty("pokemonid")
    val pokemonId: Int,

    @Column(name = "name")
    val name: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "pokemontypeid")
    val pokemonTypes: PokemonTypes,

    @Column(name = "height")
    val height: Double,

    @Column(name = "weight")
    val weight: Double,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "pokemonabilityid")
    val pokemonAbilities: PokemonAbilities,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "egggroupid")
    val eggGroups: EggGroups,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "statid")
    val pokemonStats: PokemonStats,

    @Column(name = "genus")
    val genus: String,

    @Column(name = "description")
    val description: String,
)


