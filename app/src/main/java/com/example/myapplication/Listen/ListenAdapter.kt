package com.example.myapplication.Listen

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


// ListenAdapter.kt，是用來將資料從資料庫綁定至 RecyclerView 的各個項目中。
class ListenAdapter(private val context: Context, private val cursor: Cursor?, private var ShowAnswerBoolean: Boolean) :
    RecyclerView.Adapter<ListenAdapter.ListenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListenViewHolder {
        // 創建 ViewHolder 並用來連結 在 layout 檔當中的 item_listen.xml
        val view = LayoutInflater.from(context).inflate(R.layout.test, parent, false)
        return ListenViewHolder(view)
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ListenViewHolder, position: Int) {
        cursor?.moveToPosition(position)    // 移動 Cursor 到指定位置
        // 將資料設定到 ViewHolder 的 TextView 中
        holder.noTextView.text = "No: " + cursor?.getInt(cursor.getColumnIndex("No")).toString()
        holder.questionTextView.text = "Question: " + cursor?.getString(cursor.getColumnIndex("Questions"))
        holder.option1TextView.text = "Option 1: " + cursor?.getString(cursor.getColumnIndex("Option_1"))
        holder.option2TextView.text = "Option 2: " + cursor?.getString(cursor.getColumnIndex("Option_2"))
        holder.option3TextView.text = "Option 3: " + cursor?.getString(cursor.getColumnIndex("Option_3"))
        holder.answerTextView.text = "Answer : " + cursor?.getString(cursor.getColumnIndex("Answer"))

        holder.option1TextView.visibility = View.GONE
        holder.option2TextView.visibility = View.GONE
        holder.option3TextView.visibility = View.GONE
        holder.answerTextView.visibility = View.GONE
        holder.swich1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                holder.option1TextView.visibility = View.VISIBLE
                holder.option2TextView.visibility = View.VISIBLE
                holder.option3TextView.visibility = View.VISIBLE
                if(ShowAnswerBoolean){
                    holder.answerTextView.visibility = View.VISIBLE
                }
            } else {
                // Switch 没有被选中时，隐藏其他视图
                holder.option1TextView.visibility = View.GONE
                holder.option2TextView.visibility = View.GONE
                holder.option3TextView.visibility = View.GONE
                holder.answerTextView.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0   // 返回項目數量或 0
    }
    fun setShowAnswerBoolean(value: Boolean) {
        ShowAnswerBoolean = value
        notifyDataSetChanged()
    }

    inner class ListenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val swich1: Switch = itemView.findViewById(R.id.switch2)
        // 宣告 ViewHolder 中的各個 TextView
        val noTextView: TextView = itemView.findViewById(R.id.textViewNo)
        val questionTextView: TextView = itemView.findViewById(R.id.textViewQuestion2)
        val option1TextView: TextView = itemView.findViewById(R.id.textViewOption1)
        val option2TextView: TextView = itemView.findViewById(R.id.textViewOption2)
        val option3TextView: TextView = itemView.findViewById(R.id.textViewOption3)
        val answerTextView: TextView = itemView.findViewById(R.id.textViewAnswer)
    }
}
