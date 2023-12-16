package com.example.myapplication.Quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.myapplication.R

class Quiz_Area : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_area)

        val BackImage = findViewById<ImageView>(R.id.backImage)
        BackImage.setOnClickListener {
            finish() // 回到上一頁
        }
        val StartQuizBTN = findViewById<Button>(R.id.startquizBTN)
        StartQuizBTN.setOnClickListener {
            val intent = Intent(this, Quiz_Process::class.java)
            startActivity(intent)
        }
    }
}