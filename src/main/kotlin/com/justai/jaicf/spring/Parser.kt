package com.justai.jaicf.spring

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class Parser {
    companion object {
        private val data: File = File("answers.csv")
        fun parseCSV() = csvReader().readAll(data)
    }

}