package com.bushelpowered.pokedex.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*


@Entity
@Table(name = "pokemon_table") // All tables need to be named with _ notation
@JsonIgnoreProperties("pokemonid")
data class Pokemon(
    // should be "id"
    @Id
    @Column(name = "pokemonid") // All names in @Column need to be lowercase
    @JsonProperty("pokemonid")
    val pokemonId: Int,

    @Column(name = "name")
    val name: String,

    // should this be one to one? Or should the types just be entered in once?
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "pokemontypeid")
    val pokemonTypes: PokemonTypes,

    @Column(name = "height")
    val height: Double,

    @Column(name = "weight")
    val weight: Double,

    // should this be one to one? Or should the abilities just be entered in once?
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "pokemonabilityid")
    val pokemonAbilities: PokemonAbilities,

    // should this be one to one? Or should the abilities just be entered in once?
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "egggroupid")
    val eggGroups: EggGroups,

    // be mindful of "Cascade"
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "pokemonid", referencedColumnName = "statid")
    val pokemonStats: PokemonStats,

    @Column(name = "genus")
    val genus: String,

    @Column(name = "description")
    val description: String,
)


