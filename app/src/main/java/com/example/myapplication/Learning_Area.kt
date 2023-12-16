package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.myapplication.vocab.Learning_Vocab

class Learning_Area : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_area)
        val learningButton = findViewById<Button>(R.id.learning_vocabBTN)
        val quizButton = findViewById<Button>(R.id.questionbankBTN)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        learningButton.setOnClickListener {
            val intent = Intent(this, Learning_Vocab::class.java)
            startActivity(intent)
        }

        quizButton.setOnClickListener {
            val intent = Intent(this, QuestionBank::class.java)
            startActivity(intent)
        }

        val BackImage = findViewById<ImageView>(R.id.backImage)
        BackImage.setOnClickListener {
            finish() // 回到上一頁
        }

    }
}