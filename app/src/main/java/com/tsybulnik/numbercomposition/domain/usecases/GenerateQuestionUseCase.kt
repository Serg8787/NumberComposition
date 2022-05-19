package com.tsybulnik.numbercomposition.domain.usecases

import com.tsybulnik.numbercomposition.domain.entitities.Question
import com.tsybulnik.numbercomposition.domain.repository.Repository

class GenerateQuestionUseCase(
    private val repository: Repository
) {
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTION)

    }

    private companion object {
        const val COUNT_OF_OPTION = 6
    }
}