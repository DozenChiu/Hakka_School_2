package com.example.myapplication.Quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class Quiz_Area : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_area)

        val receivedIntent = intent
        val accuracy = receivedIntent.getDoubleExtra("accuracy", 0.0)

        val Score = findViewById<TextView>(R.id.Score)
        Score.text = "Your Scores: " + String.format("%.2f",accuracy)
        Score.textSize = 30f  // 設置文本大小

        val BackImage = findViewById<ImageView>(R.id.backImage)
        BackImage.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val StartQuizBTN = findViewById<Button>(R.id.startquizBTN)
        StartQuizBTN.setOnClickListener {
            val intent = Intent(this, Quiz_Process::class.java)
            startActivity(intent)
        }
    }
}