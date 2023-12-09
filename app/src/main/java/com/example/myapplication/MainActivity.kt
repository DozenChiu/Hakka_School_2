package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val learningButton = findViewById<Button>(R.id.learning_areaBTN)
        val quizButton = findViewById<Button>(R.id.quiz_areaBTN)
        val traceButton = findViewById<Button>(R.id.traceBTN)
        learningButton.setOnClickListener {
            val intent = Intent(this, Learning_Area::class.java)
            startActivity(intent)
        }
        quizButton.setOnClickListener {
            val intent = Intent(this, Quiz_Area::class.java)
            startActivity(intent)
        }
        traceButton.setOnClickListener {
            val intent = Intent(this, Trace::class.java)
            startActivity(intent)
        }
    }
}