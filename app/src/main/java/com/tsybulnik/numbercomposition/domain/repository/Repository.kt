package com.tsybulnik.numbercomposition.domain.repository

import com.tsybulnik.numbercomposition.domain.entitities.GameSettings
import com.tsybulnik.numbercomposition.domain.entitities.Level
import com.tsybulnik.numbercomposition.domain.entitities.Question

interface Repository {

    fun generateQuestion(maxSumValue:Int, countOfOptions:Int):Question

    fun getGameSettings(level: Level):GameSettings
}