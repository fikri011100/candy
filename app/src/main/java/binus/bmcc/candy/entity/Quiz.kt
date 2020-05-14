package binus.bmcc.candy.entity

import androidx.annotation.IntegerRes

data class Quiz (
    val answer: IntegerRes?,
    val question: IntegerRes?,
    val answerId: IntegerRes?
)