package com.bushelpowered.pokedex.service

import java.io.File
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class PokemonCsvParser() {
    fun parseCSV(): List<List<String>> {
        val filePath = "src/main/resources/db/changelog/data/"
        val file = filePath + "pokedex.csv"
        return csvReader().readAll(File(file))
    }

    fun formatToStringList(str: String): MutableList<String> {
        return str.replace("[\"", "")
            .replace("\"]", "")
            .replace("\", \"", ", ")
            .replace("é", "e")
            .replace(("[0-9]").toRegex(), "")
            .split(", ")
            .toMutableList()
    }
}

