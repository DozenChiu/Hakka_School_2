package com.example.myapplication.vocab

import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DatabaseHelper
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLearningVocabBinding

class Learning_Vocab : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper   // 建立 DatabaseHelper
    private lateinit var recyclerView: RecyclerView // 宣告 RecyclerView
    private lateinit var wordListAdapter: WordListAdapter
    private var cursor: Cursor? = null // 宣告 Cursor 來存放資料
    private lateinit var binding: ActivityLearningVocabBinding   // 用來綁定layout的物件
    private lateinit var spinner: Spinner
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLearningVocabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner = binding.spinner // 下拉表單的宣告
        val listItems = resources.getStringArray(R.array.wordType)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listItems)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{ // 下拉表單的動作
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@Learning_Vocab, "You have selected $selectedItem", Toast.LENGTH_SHORT).show()

                // 在 Spinner 選擇後執行更新 RecyclerView 的操作
                updateWordList(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        searchView = binding.searchView
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 當用戶按下軟鍵盤上的搜尋按鈕時調用
                // 這裡你可以執行實際的搜尋操作，例如調用 searchInDatabase 函數
                if (!query.isNullOrEmpty()) {
                    // 有搜尋文字，執行搜尋操作
                    performSearch(query)
                } else {
                    // 沒有搜尋文字，使用 Spinner 的選擇進行篩選
                    val selectedCategory = spinner.selectedItem.toString()
                    // 使用 indexOf 方法找到 selectedCategory 在 listItems 中的索引
                    val index = listItems.indexOf(selectedCategory)
                    // 如果沒有搜尋文字且 Spinner 選擇未變，你可以在這裡處理回到 Spinner 的邏輯
                    // 例如，將 Spinner 的選擇設置為預設值
                    spinner.setSelection(index)

                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                // 當用戶輸入或修改搜尋文字時調用
                // 這裡你可以實時響應用戶的輸入，例如篩選搜尋結果
                newText?.let {

                }
                return true
            }
        })


        dbHelper = DatabaseHelper(this)     // 初始化 DatabaseHelper
        cursor = dbHelper.getWordListData(0)         // 取得 wordList 資料表資料

        recyclerView = binding.recyclerView // 找到 RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)  // 設定 RecyclerView 的 LayoutManager
        wordListAdapter = WordListAdapter(this,cursor) // 初始化 Adapter
        recyclerView.adapter = wordListAdapter   // 設定 RecyclerView 使用的 Adapter

    }

    private fun performSearch(query: String?) {
        cursor?.close()
        cursor = dbHelper.searchInDatabase(query)
        wordListAdapter.updateCursor(cursor)
    }

    private fun updateWordList(index: Int) { // 表單內部更新的宣告
        // 根據新的索引(index)更新 Cursor
        cursor?.close()  // 關閉舊的 Cursor
        cursor = dbHelper.getWordListData(index)  // 根據新的索引取得新的 Cursor
        wordListAdapter.updateCursor(cursor)  // 通知 Adapter 數據已經改變
    }

    override fun onDestroy() {
        cursor?.close()      // 關閉 Cursor 釋放資源
        dbHelper.close()    // 關閉資料庫連線
        super.onDestroy()
    }
}