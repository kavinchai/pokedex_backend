package com.bushelpowered.pokedex.dataClasses

import javax.persistence.*


@Entity
@Table(name = "pokemon_table") // All tables need to be named with _ notation
data class Pokemon (
    @Id
    @Column(name = "pokemonid") // All names in @Column need to be lowercase
    val pokemonID: Int,

    @Column(name = "name")
    val name: String,

    @Column(name = "height")
    val height: Int,

    @Column(name = "weight")
    val weight: Int,

    @Column(name = "genus")
    val genus: String,

    @Column(name = "description")
    val description: String,

//    @OneToOne(cascade = [CascadeType.ALL])
//    @JoinColumn(name = "pokemonid", referencedColumnName = "statid")
//    val pokemonStats: PokemonStats,
//
//    @OneToOne(cascade = [CascadeType.ALL])
//    @JoinColumn(name = "pokemonid", referencedColumnName = "egggroupid")
//    val eggGroups: EggGroups,
//
//    @OneToOne(cascade = [CascadeType.ALL])
//    @JoinColumn(name = "pokemonid", referencedColumnName = "pokemonabilityid")
//    val pokemonAbilities: PokemonAbilities,
//
//    @OneToOne(cascade = [CascadeType.ALL])
//    @JoinColumn(name = "pokemonid", referencedColumnName = "pokemontypeid")
//    val pokemonTypes: PokemonTypes,
)


