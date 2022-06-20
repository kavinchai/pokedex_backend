package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.dataClasses.*
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PokemonService (val pdb: PokemonRepository) {
    fun createPokemonDb() {
        pdb.saveAll(parseFile().listOfPokemon())
    }

    fun allPokemon(): Iterable<Pokemon> = pdb.findAll()

    fun getPokemon(id: Int): Optional<Pokemon> = pdb.findById(id)

    fun getPokemonByPage(pageNum: Int, pageSize: Int): Iterable<Pokemon> {
        return pdb.findAll(PageRequest.of(pageNum, pageSize))
    }
}