package com.tsybulnik.numbercomposition.data

import com.tsybulnik.numbercomposition.domain.entitities.GameSettings
import com.tsybulnik.numbercomposition.domain.entitities.Level
import com.tsybulnik.numbercomposition.domain.entitities.Question
import com.tsybulnik.numbercomposition.domain.repository.Repository
import java.lang.Math.max
import java.lang.Math.min
import kotlin.random.Random

object GameRepositoryImp : Repository {
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> GameSettings(10, 3, 50, 8)
            Level.EASY -> GameSettings(10, 10, 70, 60)
            Level.MIDDLE -> GameSettings(20, 20, 80, 50)
            Level.HARD -> GameSettings(30, 30, 90, 45)
        }
    }
}