package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.dataClasses.Pokemon
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface PokemonRepository : CrudRepository<Pokemon, Int> {
    abstract fun findAll(paging: Pageable): MutableIterable<Pokemon>
}