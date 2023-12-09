package com.example.myapplication

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListenOne : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper   // 建立 DatabaseHelper
    private lateinit var recyclerView: RecyclerView // 宣告 RecyclerView
    private lateinit var listenAdapter: ListenAdapter   // 宣告 Cursor 來存放資料
    private var cursor: Cursor? = null

    // 這邊的功能是初始化了 DatabaseHelper 以及從 Listen_1 or ( Listen_2 ) 資料表取得資料並顯示在 RecyclerView 中。
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listenone)  // 設定畫面

        val BackImage = findViewById<ImageView>(R.id.backImage)
        BackImage.setOnClickListener {
            finish() // 回到上一頁
        }

        dbHelper = DatabaseHelper(this)     // 初始化 DatabaseHelper
        cursor = dbHelper.getListen1Data()         // 取得資料表資料Listen_1
        //cursor = dbHelper.getListen2Data()          取得資料表資料Listen_2

        recyclerView = findViewById(R.id.recyclerView)  // 找到 RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)  // 設定 RecyclerView 的 LayoutManager
        listenAdapter = ListenAdapter(this, cursor) // 初始化 Adapter
        recyclerView.adapter = listenAdapter    // 設定 RecyclerView 使用的 Adapter
    }

    override fun onDestroy() {
        cursor?.close()      // 關閉 Cursor 釋放資源
        dbHelper.close()    // 關閉資料庫連線
        super.onDestroy()
    }
}
