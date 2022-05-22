package com.tsybulnik.numbercomposition.domain.entitities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class GameResult (
    val winner:Boolean,
    val countOfRightAnswers:Int,
    val countOfQustion:Int,
    val gameSettings: GameSettings
        ) : Parcelable