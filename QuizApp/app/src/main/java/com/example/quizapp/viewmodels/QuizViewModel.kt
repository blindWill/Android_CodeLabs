package com.example.quizapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val repo: Repo): ViewModel() {
    val currentQuestionId = MutableLiveData(0)
    val currentQuestion = MutableLiveData<String>()
    var userAnswers = Array<Int>(getQuestionsAmount()){0}
    var userAnswer = -1

    fun getQuestionsAmount() = repo.data.size

    fun loadNextQuestion(){
        currentQuestionId.value = currentQuestionId.value!! + 1
        currentQuestion.value = repo.data[currentQuestionId.value!!]
    }

    fun loadPreviousQuestion() {
        currentQuestionId.value = currentQuestionId.value!! -1
        currentQuestion.value = repo.data[currentQuestionId.value!!]
    }

    fun loadCurrentQuestion() {
        currentQuestion.value = repo.data[currentQuestionId.value!!]
    }

    fun saveUserAnswer() {
        userAnswers[currentQuestionId.value!!] = userAnswer
    }

    fun loadAnswers(questionNumber: Int):ArrayList <String> {
        return repo.answersAsVariants[questionNumber]
    }

    fun getRightAmount(): Int {
        var correctAnswers = 0
        for (i in userAnswers.indices) {
            if (userAnswers[i] == repo.answersId[i]) {
                correctAnswers++
            }
        }
        return correctAnswers
    }

    fun getComment(): String {
        val res = getRightAmount()
        return when {
            res == getQuestionsAmount() -> "Excellent!"
            res > getQuestionsAmount() * 2 / 3 -> "Good job!"
            res > getQuestionsAmount() / 3 -> "Practice makes perfect!"
            else -> "Try again!"
        }
    }

    fun restartQuiz() {
        currentQuestionId.postValue(0)
        userAnswer = -1
        for (i in userAnswers.indices) {
            userAnswers[i] = -1
        }
    }

    fun getResultMessage(): String {
        return "Try english tenses quiz! I got ${getRightAmount()}/${getQuestionsAmount()}! Now your turn!"
    }
}