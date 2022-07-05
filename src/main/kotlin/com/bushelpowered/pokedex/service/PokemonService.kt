package com.bushelpowered.pokedex.service

import com.bushelpowered.pokedex.repository.PokemonRepository
import com.bushelpowered.pokedex.entity.*
import com.bushelpowered.pokedex.repository.PokemonTypeRepository
import com.bushelpowered.pokedex.repository.TypeRepository
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class PokemonService(
    private val pokemonRepository: PokemonRepository,
    private val pokemonTypeRepository: PokemonTypeRepository,
    private val typeRepository: TypeRepository
){
    fun getPokemonById(id: Int): Pokemon? {
       return pokemonRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun getPokemonByName(name: String): Any {
        val pokemonList =  pokemonRepository.findAll()
        val listOfPokemonId = mutableListOf<Int>()
        val listOfTypeModels = mutableListOf<Type>()
        for (pokemon in pokemonList){
            if (pokemon.name.lowercase() == name.lowercase()){
                val pokemonId = pokemon.id
                val pokemonTypeRepository = pokemonTypeRepository.findAll()
                for (i in pokemonTypeRepository){
                    if (i.pokemonId == pokemonId){
                        listOfPokemonId.add(i.typeId)
                    }
                }
                for (e in listOfPokemonId){
                    val pokemonType = typeRepository.findById(e).orElse(null)
                    listOfTypeModels.add(pokemonType)
                }
                println(listOfTypeModels)
                return pokemon
            }
        }
        return ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getPokemonByPage(pageNum: Int, pageSize: Int): Iterable<Pokemon> {
        return pokemonRepository.findAll(PageRequest.of(pageNum, pageSize))
    }
//    private fun <E> MutableList<E>.add(element: Optional<E>) {}
}
