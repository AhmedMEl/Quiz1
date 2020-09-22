package com.example.quiz1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val questionBank = listOf(
        Question(R.string.question_australia, true,0),
        Question(R.string.question_oceans, true,0),
        Question(R.string.question_mideast, false,0),
        Question(R.string.question_africa, false,0),
        Question(R.string.question_americas, true,0),
        Question(R.string.question_asia, true,0))
    private var currentIndex = 0
    private var degree = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_true.setOnClickListener{
            //           Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
            checkAnswer(true)
        }

        btn_false.setOnClickListener {
            //            val toast:Toast = Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_SHORT);
//            toast .setGravity(Gravity.TOP, 0, 0);
//            toast.show();
            checkAnswer(true)

        }
        btn_next.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
//        *********************************** challenge 1 *********************************************
        question_text_view.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
//        *********************************** challenge 2 *********************************************
        btn_Previous.setOnClickListener{

            currentIndex = (currentIndex - 1) % questionBank.size
            if(currentIndex == -1){
                currentIndex= questionBank.size -1
            }

            updateQuestion()
        }
        updateQuestion()

    }
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        question_text_view.setText(questionTextResId)
        if(questionBank[currentIndex].status==1){
            btn_false.isEnabled = false
            btn_true.isEnabled=false
        }else{
            btn_false.isEnabled = true
            btn_true.isEnabled=true
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast

        } else {
            R.string.incorrect_toast
        }
        if(userAnswer == correctAnswer){
            questionBank[currentIndex].status=1
            degree+=1
        }else{
            questionBank[currentIndex].status=1
        }

//            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
//                    .show()
        val toast: Toast = Toast.makeText(this,messageResId, Toast.LENGTH_SHORT);
        toast .setGravity(Gravity.TOP, 0, 0);
        toast.show();

        if(currentIndex==questionBank.size -1){
            Toast.makeText(this,"The degree for answer = $degree",Toast.LENGTH_SHORT).show()
            degree=0
            for (i in 0..questionBank.size-1){
                questionBank[i].status=0
            }
        }
    }

}
