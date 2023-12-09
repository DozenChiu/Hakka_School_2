package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Learning_Vocab : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_vocab)

        val BackImage = findViewById<ImageView>(R.id.backImage)
        BackImage.setOnClickListener {
            finish() // 回到上一頁
        }
    }
}