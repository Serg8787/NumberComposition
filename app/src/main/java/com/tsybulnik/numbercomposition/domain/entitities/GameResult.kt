package com.tsybulnik.numbercomposition.domain.entitities

data class GameResult (
    val winner:Boolean,
    val countOfRightAnswers:Int,
    val countOfQustion:Int,
    val gameSettings: GameSettings
        ){
}