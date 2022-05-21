package com.tsybulnik.numbercomposition.presentaion

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tsybulnik.numbercomposition.R
import com.tsybulnik.numbercomposition.data.GameRepositoryImp
import com.tsybulnik.numbercomposition.domain.entitities.GameResult
import com.tsybulnik.numbercomposition.domain.entitities.GameSettings
import com.tsybulnik.numbercomposition.domain.entitities.Level
import com.tsybulnik.numbercomposition.domain.entitities.Question
import com.tsybulnik.numbercomposition.domain.usecases.GenerateQuestionUseCase
import com.tsybulnik.numbercomposition.domain.usecases.GetGameSettingsUseCase

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GameRepositoryImp
    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level

    private val context = application

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingUseCase = GetGameSettingsUseCase(repository)

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String>
        get() = _progressAnswers

    private val _percentOfRightAnswer = MutableLiveData<Int>()
    val percentOfRightAnswer: LiveData<Int>
        get() = _percentOfRightAnswer

    private val _enouhgCountOfRightAnswer = MutableLiveData<Boolean>()
    val enoughCountOfRightAnswer: LiveData<Boolean>
        get() = _enouhgCountOfRightAnswer

    private val _enouhgPercentOfRightAnswer = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswer: LiveData<Boolean>
        get() = _enouhgPercentOfRightAnswer

    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    private var timer: CountDownTimer? = null
    private var countOfRightAnswers = 0
    private var countOfRightQuestion = 0

    fun startGame(level: Level) {
        getGameSettings(level)
        startTimer()
        generateQuestion()
        updateProgress()
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)

    }

    private fun getGameSettings(level: Level) {
        this.level=level
        gameSettings = getGameSettingUseCase(level)
        _minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLS_IN_SECONDS, MILLS_IN_SECONDS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)

            }

            override fun onFinish() {
                finishGame()
            }

        }
        timer?.start()
    }

    private fun finishGame() {
       _gameResult.value = GameResult(
            enoughCountOfRightAnswer.value == true
                    && _enouhgPercentOfRightAnswer.value == true,
            countOfRightAnswers=countOfRightAnswers,
            countOfQustion = countOfRightQuestion,
            gameSettings=gameSettings

        )

    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTE
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTE)
        return String.format("%02d:%02d", minutes, leftSeconds)

    }

    fun updateProgress() {
        val percent = calculatePercentOfRightAnswers()
        _percentOfRightAnswer.value = percent
        _progressAnswers.value = String.format(
            context.resources.getString(R.string.progress_answers),
            countOfRightAnswers, gameSettings.minCountOfRightAnswers
        )
        _enouhgCountOfRightAnswer.value = countOfRightAnswers >= gameSettings.minCountOfRightAnswers
        _enouhgPercentOfRightAnswer.value = percent >= gameSettings.minPercentOfRightAnswers

    }

    private fun calculatePercentOfRightAnswers(): Int {
        if(countOfRightQuestion==0) { return 0}
            return (countOfRightAnswers / countOfRightQuestion.toDouble() * 100).toInt()
    }

    fun chooseAnswer(number: Int) {
        checkAnswer(number)
        updateProgress()
        generateQuestion()
    }

    private fun checkAnswer(number: Int) {
        val rightAnswer = question.value?.rightAnswer
        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfRightQuestion++

    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        private const val MILLS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTE = 60
    }
}