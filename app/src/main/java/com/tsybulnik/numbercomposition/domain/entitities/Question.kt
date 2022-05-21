package com.tsybulnik.numbercomposition.domain.entitities

data class Question(
    val sum:Int,
    val visibleNumber:Int,
    val options:List<Int>,
    val rightAnswer: Int = sum - visibleNumber

)