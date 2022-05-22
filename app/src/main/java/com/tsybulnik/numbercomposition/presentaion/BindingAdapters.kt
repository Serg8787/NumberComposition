package com.tsybulnik.numbercomposition.presentaion

import android.provider.Settings.Global.getString
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.tsybulnik.numbercomposition.R
import com.tsybulnik.numbercomposition.domain.entitities.GameResult

@BindingAdapter("requiredAnswers")

fun bindRequireAnswers(textView: TextView,count:Int){
    textView.text = String.format(
                textView.context.getString(R.string.required_score),
                count
            )

}
@BindingAdapter("score")
fun bindScore(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        score
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percentage
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswers(gameResult)
    )
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (gameResult.countOfQustion == 0) {
        0
    } else {
        ((countOfRightAnswers / gameResult.countOfQustion.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("resultEmoji")
fun bindResultEmoji(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.icons8_smile
    } else {
        R.drawable.icons8_sad
    }
}