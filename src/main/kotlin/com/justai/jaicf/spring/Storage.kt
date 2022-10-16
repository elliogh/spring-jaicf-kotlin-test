package com.justai.jaicf.spring

data class Storage(
    val questionList: List<String>,
    val answerOptions: Map<Int, Array<String>>,
    val correctAnswers: List<String>
)