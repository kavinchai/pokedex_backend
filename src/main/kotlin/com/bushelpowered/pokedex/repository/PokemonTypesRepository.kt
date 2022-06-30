package com.bushelpowered.pokedex.repository

import com.bushelpowered.pokedex.entity.PokemonTypes
import org.springframework.data.repository.CrudRepository

interface PokemonTypeRepository : CrudRepository<PokemonTypes, Int>{
}
