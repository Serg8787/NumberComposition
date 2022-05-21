package com.tsybulnik.numbercomposition.domain.entitities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
enum class Level : Parcelable {
     TEST, EASY, MIDDLE, HARD
}