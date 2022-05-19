package com.tsybulnik.numbercomposition.domain.usecases

import com.tsybulnik.numbercomposition.domain.entitities.GameSettings
import com.tsybulnik.numbercomposition.domain.entitities.Level
import com.tsybulnik.numbercomposition.domain.entitities.Question
import com.tsybulnik.numbercomposition.domain.repository.Repository

class GetGameSettingsUseCase (
    private val repository: Repository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)

    }


}