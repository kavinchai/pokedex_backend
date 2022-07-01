package com.bushelpowered.pokedex

import com.bushelpowered.pokedex.repository.*
import com.bushelpowered.pokedex.service.PopulateData
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener

@SpringBootApplication
class PokedexApplication

fun main(args: Array<String>) {
    runApplication<PokedexApplication>(*args)
}
