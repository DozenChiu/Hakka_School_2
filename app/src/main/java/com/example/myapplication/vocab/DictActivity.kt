package com.example.myapplication.vocab

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHelper
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDictBinding
import com.example.myapplication.databinding.ActivityLearningVocabBinding

class DictActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDictBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDictBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 獲取傳遞過來的字串
        val receivedWord = intent.getStringExtra("word")
        val receiveSymbol = intent.getStringExtra("symbol")
        val receiveRemain = intent.getStringExtra("remain")

        // 將字串設置到 TextView 中
        binding.textVocab.text = receivedWord
        binding.textSymbol.text = receiveSymbol
        binding.textRemain.text = receiveRemain
    }
}