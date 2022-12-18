package com.example.quizapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuestionBinding
import com.example.quizapp.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private val viewModel:QuizViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpViews()
    }

    private fun setUpViews() {
        binding.previousButton.setOnClickListener {
            viewModel.saveUserAnswer()
            viewModel.loadPreviousQuestion()
        }

        binding.progressBar.max = viewModel.getQuestionsAmount()

        viewModel.loadCurrentQuestion()

        binding.answersRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.firstAnswerRB -> {
                    viewModel.userAnswer = 1
                }
                R.id.secondAnswerRB -> {
                    viewModel.userAnswer = 2
                }
                R.id.thirdAnswerRB -> {
                    viewModel.userAnswer = 3
                }
                R.id.fourthAnswerRB -> {
                    viewModel.userAnswer = 4
                }
            }
        }
    }

    private fun setUpObservers() {
        viewModel.currentQuestionId.observe(viewLifecycleOwner) { questionNumber ->
            viewModel.loadCurrentQuestion()//
            binding.previousButton.isEnabled = questionNumber != 0
            if (questionNumber == viewModel.getQuestionsAmount() - 1) {
                binding.nextButton.text = "Finish"
                binding.nextButton.setOnClickListener {
                    viewModel.saveUserAnswer()
                    findNavController().navigate(R.id.action_questionFragment_to_quizResultFragment)
                }
            } else {
                binding.nextButton.text = "next"
                binding.nextButton.setOnClickListener {
                    viewModel.saveUserAnswer()
                    viewModel.loadNextQuestion()
                }
            }

            binding.progressBar.progress = viewModel.currentQuestionId.value!!
            setUpAnswers(questionNumber)
        }

        viewModel.currentQuestion.observe(viewLifecycleOwner) { question ->
            binding.questionTextView.text = question
            loadUserAnswerIfExistAtQuestion(viewModel.currentQuestionId.value!!)
        }
    }

    private fun loadUserAnswerIfExistAtQuestion(questionNumber: Int) {
        when (viewModel.userAnswers[questionNumber]) {
            1 -> {
                binding.answersRadioGroup.check(R.id.firstAnswerRB)
                viewModel.userAnswer = 1
            }
            2 -> {
                binding.answersRadioGroup.check(R.id.secondAnswerRB)
                viewModel.userAnswer = 2
            }
            3 -> {
                binding.answersRadioGroup.check(R.id.thirdAnswerRB)
                viewModel.userAnswer = 3
            }
            4 -> {
                binding.answersRadioGroup.check(R.id.fourthAnswerRB)
                viewModel.userAnswer = 4
            }
            else -> {
                binding.answersRadioGroup.clearCheck()
                viewModel.userAnswer = -1
            }
        }
    }

    private fun setUpAnswers(questionNumber: Int) {
        val answers = viewModel.loadAnswers(questionNumber)
        binding.firstAnswerRB.text = answers[0]
        binding.secondAnswerRB.text = answers[1]
        binding.thirdAnswerRB.text = answers[2]
        binding.fourthAnswerRB.text = answers[3]
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}