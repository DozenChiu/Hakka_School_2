package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class QuestionBank : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_bank)
        val ListenOneButton = findViewById<Button>(R.id.ListenOneBTN)
        val ListenTwoButton = findViewById<Button>(R.id.ListenTwoBTN)
        val ReadingButton = findViewById<Button>(R.id.ReadingBTN)
        ListenOneButton.setOnClickListener {
            val intent = Intent(this, ListenOne::class.java)
            startActivity(intent)
        }

        ListenTwoButton.setOnClickListener {
            val intent = Intent(this, ListenTwo::class.java)
            startActivity(intent)
        }

        ReadingButton.setOnClickListener {
            val intent = Intent(this, Reading::class.java)
            startActivity(intent)
        }
        val backImage = findViewById<ImageView>(R.id.backImage)
        backImage.setOnClickListener {
            finish() // 回到上一頁
        }
    }
}