package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.dto.PokemonResponse
import com.bushelpowered.pokedex.entity.Pokemon
import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class PokedexApplication {
    // perhaps here, we could put the OnStartup Listener
}

fun main(args: Array<String>) {
    runApplication<PokedexApplication>(*args)
}


/*

    Other general comments

    consider your controllers, should they be broken up more?
    e.g.    AuthController
            CaptureController
            TrainerController
            PokemonController


    Try writing a unit test using "MockK"

    Try to use your endpoints in unorthodox ways.
    What happens if you query all 300+ Pokemon at once, should your request params be validated?


    Be Explicit, but not wordy in your variable and method names
    Make sure you handle edge cases
    Sometimes, elegance in code can be counter productive.
    Think, if I came back to this code in 3 months, how long will it take to remember
    how and why my code works. What if you need to add a feature? A query param? Etc.




    Use Model / Entity / Dto (Request + Response)
    and write mappers to & from each as above.

    Validate your RequestDtos
    try "Valiktor" in your user registration

    only expose methods that are absolutely necessary
    Only build features that are required. If you want to add or modify features in the "real"
    world, it is a full team discussion / process.
 */

//    Write Mappers like this:
fun Pokemon.toPokemonResponse(): PokemonResponse {
    return PokemonResponse(
            pokemonId = this.pokemonId,

    )
}