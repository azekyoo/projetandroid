package fr.epf.min1.projetandroid

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.epf.min1.projetandroid.R
import fr.epf.min1.projetandroid.QuizData
import fr.epf.min1.projetandroid.QuizQuestion

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var nextButton: Button

    private var currentQuestionIndex = 0
    private var score = 0

    private val questions: List<QuizQuestion> = QuizData.questions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTextView = findViewById(R.id.questionTextView)
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup)
        nextButton = findViewById(R.id.nextButton)

        loadQuestion()

        nextButton.setOnClickListener {
            checkAnswer()
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                loadQuestion()
            } else {
                showResults()
            }
        }
    }

    private fun loadQuestion() {
        val question = questions[currentQuestionIndex]
        questionTextView.text = question.question
        optionsRadioGroup.removeAllViews()

        question.options.forEachIndexed { index, option ->
            val radioButton = RadioButton(this).apply {
                text = option
                id = index
            }
            optionsRadioGroup.addView(radioButton)
        }
    }

    private fun checkAnswer() {
        val selectedOptionId = optionsRadioGroup.checkedRadioButtonId
        if (selectedOptionId == questions[currentQuestionIndex].correctAnswer) {
            score++
        }
    }

    private fun showResults() {
        questionTextView.text = "Quiz Over! Your score is $score/${questions.size}"
        optionsRadioGroup.removeAllViews()
        nextButton.isEnabled = false
    }
}
