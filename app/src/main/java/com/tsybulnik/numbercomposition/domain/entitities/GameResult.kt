package com.tsybulnik.numbercomposition.domain.entitities

import java.io.Serializable

data class GameResult (
    val winner:Boolean,
    val countOfRightAnswers:Int,
    val countOfQustion:Int,
    val gameSettings: GameSettings
        ) : Serializable