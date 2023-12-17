package com.example.myapplication.Quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHelper
import com.example.myapplication.R

class Quiz_Process : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var quizAdapter: QuizAdapter
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_process)

        dbHelper = DatabaseHelper(this)
        val quizQuestions = dbHelper.getQuizQuestions()

        recyclerView = findViewById(R.id.quizRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        quizAdapter = QuizAdapter(quizQuestions)
        recyclerView.adapter = quizAdapter


        findViewById<Button>(R.id.submitQuizButton).setOnClickListener {
            val correctAnswers = quizAdapter.getCorrectAnswers()
            val totalQuestions = quizAdapter.itemCount
            val accuracy = (correctAnswers.toDouble() / totalQuestions) * 100
            //val accuracy = correctAnswers
            Toast.makeText(this, "Accuracy: $accuracy%", Toast.LENGTH_SHORT).show()

            // 跳轉到 Quiz_History 頁面
            /*val intent = Intent(this, Quiz_History::class.java)
            startActivity(intent)*/
        }
    }
}