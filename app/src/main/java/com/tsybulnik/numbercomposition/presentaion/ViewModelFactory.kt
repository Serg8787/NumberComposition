package com.tsybulnik.numbercomposition.presentaion

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tsybulnik.numbercomposition.domain.entitities.Level

class ViewModelFactory(
    private val level: Level,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(level = level, application = application) as T
        } else {
            throw RuntimeException("Unknown viewModelClass")
        }
    }
}