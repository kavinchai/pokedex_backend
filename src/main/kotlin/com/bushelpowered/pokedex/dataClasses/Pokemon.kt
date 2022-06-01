package com.bushelpowered.pokedex.dataClasses

import javax.persistence.*


@Entity
@Table(name = "pokemon_table") // All tables need to be named with _ notation
data class Pokemon (
    @Id
    @Column(name = "pokemonid") // All names in @Column need to be lowercase
    val PokemonID: Int,

    @Column(name = "name")
    val Name: String,

    @Column(name = "height")
    val Height: String,

    @Column(name = "weight")
    val Weight: String,

    @Column(name = "genus")
    val Genus: String,

    @Column(name = "description")
    val Description: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "statid")
    val pokemonStats: PokemonStats,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "egggroupid")
    val eggGroups: EggGroups,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "pokemonabilityid")
    val pokemonAbilities: PokemonAbilities,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "pokemontypeid")
    val pokemonTypes: PokemonTypes,
)


