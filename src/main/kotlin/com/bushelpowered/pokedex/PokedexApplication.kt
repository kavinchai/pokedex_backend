package com.bushelpowered.pokedex

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class PokedexApplication

fun main(args: Array<String>) {
    runApplication<PokedexApplication>(*args)
}
