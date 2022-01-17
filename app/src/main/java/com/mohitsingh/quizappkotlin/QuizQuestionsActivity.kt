package com.mohitsingh.quizappkotlin

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() {

    private val currentPosition = 1
    private var questionsList: ArrayList<Question>? = null
    private var currentOption = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        supportActionBar?.title = "Quiz"

        questionsList = Constants.getQuestions()

        setQuestion()
        setupOptionsOnClick()

    }

    private fun setQuestion() {
        val currentQuestion = questionsList!![currentPosition - 1]

        setDefaultView()

        tv_question.text = currentQuestion.question
        iv_image.setImageResource(currentQuestion.image)
        progressBar.progress = currentQuestion.id
        progressBar.progressDrawable.setColorFilter(
            resources.getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
        tv_progress.text = "${currentQuestion.id}/${progressBar.max}"
        tv_option_one.text = currentQuestion.optionOne
        tv_option_two.text = currentQuestion.optionTwo
        tv_option_three.text = currentQuestion.optionThree
        tv_option_four.text = currentQuestion.optionFour
    }

    private fun setDefaultView() {
        val listOfTextViews = ArrayList<TextView>()
        listOfTextViews.add(0, tv_option_one)
        listOfTextViews.add(1, tv_option_two)
        listOfTextViews.add(2, tv_option_three)
        listOfTextViews.add(3, tv_option_four)

        for (element in listOfTextViews) {
            element.setTextColor(Color.parseColor("#7A8089"))
            element.typeface = Typeface.DEFAULT
            element.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun setupOptionsOnClick() {
        tv_option_one.setOnClickListener(View.OnClickListener {
            onOptionSelect(tv_option_one, 1)
        })
        tv_option_two.setOnClickListener(View.OnClickListener {
            onOptionSelect(tv_option_two, 2)
        })
        tv_option_three.setOnClickListener(View.OnClickListener {
            onOptionSelect(tv_option_three, 3)
        })
        tv_option_four.setOnClickListener(View.OnClickListener {
            onOptionSelect(tv_option_four, 4)
        })
    }

    private fun onOptionSelect(tv: TextView, optionSelected: Int) {

        setDefaultView()

        currentOption = optionSelected

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )

    }

}
