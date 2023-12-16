/*
問題：
前端修改
update 點選已看過
音檔播放
*/
package com.example.myapplication.vocab

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemVocabBinding

class WordListAdapter(private val context: Context, private var cursor: Cursor?) :
    RecyclerView.Adapter<WordListAdapter.WordListViewHolder>() {

    fun updateCursor(newCursor: Cursor?) {
        cursor?.close()  // 關閉舊的 Cursor
        cursor = newCursor  // 使用新的 Cursor
        notifyDataSetChanged()  // 通知 RecyclerView 數據已經改變
    }

    // 改用 viewBinding 來實作，避免一直打 findViewById 上去
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        val binding = ItemVocabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordListViewHolder(binding)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        //val wordList = context.resources.getStringArray(R.array.wordType)
        cursor?.moveToPosition(position)
        holder.bind(cursor)

        holder.itemView.setOnClickListener {
            // 在點擊時執行的操作
            val p = holder.adapterPosition
            Log.d("WordListAdapter", "Clicked position: $p")
            cursor?.moveToPosition(p)
            val word = cursor?.getString(cursor!!.getColumnIndex("Word"))
            val symbol = "音標：" + cursor?.getString(cursor!!.getColumnIndex("Symbol"))
            val remain = "中文：" + cursor?.getString(cursor!!.getColumnIndex("Meaning")) + "\n\n詞性：" + cursor?.getString(cursor!!.getColumnIndex("Def_1")) + "；" +
            ( cursor?.getString(cursor!!.getColumnIndex("Def_2"))?:"") + "\n\n例句：" + (cursor?.getString(cursor!!.getColumnIndex("Example"))?:"") + "\n\n例句翻譯：" +
                    ( cursor?.getString(cursor!!.getColumnIndex("Translation"))?:"") + "\n\n備註：" + (cursor?.getString(cursor!!.getColumnIndex("Remark"))?:"")
            Log.d("WordListAdapter", "Clicked word: $word, symbol: $symbol , remain: $remain")
            val intent = Intent(context, DictActivity::class.java)
            intent.putExtra("word", word)
            intent.putExtra("symbol", symbol)
            intent.putExtra("remain", remain)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    class WordListViewHolder(private val binding: ItemVocabBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("Range")
        fun bind(cursor: Cursor?) {
            binding.apply {
                textVocab.text = cursor?.getString(cursor.getColumnIndex("Word")) ?: ""
                textSymbol.text = cursor?.getString(cursor.getColumnIndex("Symbol")) ?: ""
                textChi.text = cursor?.getString(cursor.getColumnIndex("Meaning")) ?: ""
            }
        }
    }
}
