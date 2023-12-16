package com.example.myapplication.Quiz

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class QuizAdapter(val questions: List<Question>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {
    private var correctAnswers = 0
    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tableInfoTextView : TextView = itemView.findViewById(R.id.textViewTableName)
        val questionText: TextView = itemView.findViewById(R.id.questionText)
        val option1Button: Button = itemView.findViewById(R.id.option1Button)
        val option2Button: Button = itemView.findViewById(R.id.option2Button)
        val option3Button: Button = itemView.findViewById(R.id.option3Button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val currentQuestion = questions[position]

        // 檢查是否為每個資料表的第一題
        if (position % QUESTIONS_PER_TABLE == 0) {
            val tableName = getTableNameByPosition(position)
            holder.tableInfoTextView.text = "Current table: $tableName"
            holder.tableInfoTextView.visibility = View.VISIBLE
        } else {
            holder.tableInfoTextView.visibility = View.GONE
        }
        holder.questionText.text = "No: ${currentQuestion.number}\n${currentQuestion.questionText}"
        holder.option1Button.text = currentQuestion.option1
        holder.option2Button.text = currentQuestion.option2
        holder.option3Button.text = currentQuestion.option3
        //updateButtonState(holder, currentQuestion)
        val questionNumber = currentQuestion.number
        val optionNumber = 1// 這裡假設這是選項1
        val imageName = "listen1_${questionNumber}_$optionNumber"
        val resourceId = holder.itemView.context.resources.getIdentifier(imageName, "drawable", holder.itemView.context.packageName)

        if (resourceId != 0) {
            val drawable = ContextCompat.getDrawable(holder.itemView.context, resourceId)
            holder.option1Button.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
        }

        val optionNumber2 =2// 這裡假設這是選項1
        val imageName2 = "listen1_${questionNumber}_$optionNumber"
        val resourceId2 = holder.itemView.context.resources.getIdentifier(imageName, "drawable", holder.itemView.context.packageName)

        if (resourceId != 0) {
            val drawable = ContextCompat.getDrawable(holder.itemView.context, resourceId)
            holder.option2Button.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
        }

        holder.option1Button.setOnClickListener {
            handleOptionClick(holder, position, currentQuestion.option1)

        }
        holder.option2Button.setOnClickListener {
            handleOptionClick(holder, position, currentQuestion.option2)
        }
        holder.option3Button.setOnClickListener {
            handleOptionClick(holder, position, currentQuestion.option3)
        }
        updateButtonState(holder, currentQuestion)
    }
    // 根據位置返回對應的資料表名稱
    private fun getTableNameByPosition(position: Int): String {
        return when (position) {
            in 0 until QUESTIONS_PER_TABLE -> "Listen_1"
            in QUESTIONS_PER_TABLE until (QUESTIONS_PER_TABLE * 2) -> "Listen_2"
            else -> "Reading"
        }
    }
    private fun updateButtonState(holder: QuizViewHolder, currentQuestion: Question) {
        holder.option1Button.setBackgroundColor(
            if (currentQuestion.selectedOption == currentQuestion.option1) Color.GREEN else Color.TRANSPARENT
        )
        holder.option2Button.setBackgroundColor(
            if (currentQuestion.selectedOption == currentQuestion.option2) Color.GREEN else Color.TRANSPARENT
        )
        holder.option3Button.setBackgroundColor(
            if (currentQuestion.selectedOption == currentQuestion.option3) Color.GREEN else Color.TRANSPARENT
        )
    }
    private fun handleOptionClick(holder: QuizViewHolder, position: Int, selectedOption: String) {
        val previousSelectedOption = questions[position].selectedOption
        // 檢查新選擇的選項是否與先前選擇的選項不同
        if (previousSelectedOption != selectedOption) {
            questions[position].selectedOption = selectedOption
            updateButtonState(holder, questions[position])
            val answerText = questions[position].getAnswerText()
            if (questions[position].selectedOption == answerText) {
                correctAnswers++
                notifyItemChanged(position)
            } else if (previousSelectedOption == answerText) {
                correctAnswers--
                notifyItemChanged(position)
            }
        }
    }
    fun calculateAccuracy(): Double {
        val totalQuestions = questions.size
        val accuracy = (correctAnswers.toDouble() / totalQuestions) * 100
        return accuracy
    }
    fun getCorrectAnswers(): Int {
        return correctAnswers
    }
    override fun getItemCount(): Int {
        return questions.size
    }
    companion object {
        private const val QUESTIONS_PER_TABLE = 5 // 每個資料表的問題數
    }
}