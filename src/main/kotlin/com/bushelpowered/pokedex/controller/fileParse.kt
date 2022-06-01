package com.bushelpowered.pokedex.controller

import com.bushelpowered.pokedex.dataClasses.Pokemon
import com.bushelpowered.pokedex.repository.PokemonRepository
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.nio.file.Files
import java.nio.file.Paths


class fileParser {
    fun insertData(){
        val filePath = "src/main/resources/db/changelog/data/"
        val file = filePath + "pokedex.csv"
        val bufferedReader = Files.newBufferedReader(Paths.get(file))
        val csvParser = CSVParser(bufferedReader, CSVFormat.EXCEL
            .withSkipHeaderRecord()
            .withHeader("id", "name", "types", "height", "weight", "abilities", "egg_groups", "stats", "genus", "description")
            )

        for (csvRecord in csvParser){
            val id : String = csvRecord.get("id")
            val name = csvRecord.get("name")
            val type = csvRecord.get("types")
            println("-----")
            println("id: $id")
            println("Name: $name")
            println("Type: $type")
        }
    }
}