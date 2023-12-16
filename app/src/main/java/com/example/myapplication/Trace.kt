package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Trace : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace)

        val BackImage = findViewById<ImageView>(R.id.backImage)
        BackImage.setOnClickListener {
            finish() // 回到上一頁
        }
    }

}