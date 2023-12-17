package com.example.myapplication.Quiz

import android.graphics.Color
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import java.io.IOException

class QuizAdapter(val questions: List<Question>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {
    private var correctAnswers = 0
    private var isReading = 0
    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionImage: ImageView = itemView.findViewById(R.id.questionImage)
        val tableInfoTextView : TextView = itemView.findViewById(R.id.textViewTableName)
        val questionText: TextView = itemView.findViewById(R.id.questionText)
        val option1Button: Button = itemView.findViewById(R.id.option1Button)
        val option2Button: Button = itemView.findViewById(R.id.option2Button)
        val option3Button: Button = itemView.findViewById(R.id.option3Button)
        val playButton : Button = itemView.findViewById(R.id.playSoundButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val currentQuestion = questions[position]

        // 檢查是否為每個資料表的第一題
        /*if (position % QUESTIONS_PER_TABLE == 0) {
            val tableName = getTableNameByPosition(position)
            holder.tableInfoTextView.text = "Current table: $tableName"
            holder.tableInfoTextView.visibility = View.VISIBLE
        } else {
            holder.tableInfoTextView.visibility = View.GONE
        }
        if (holder.tableInfoTextView.text == "Current table: Reading" ) {
            isReading = 1
        }*/
        val tableName = getTableNameByPosition(position)
        holder.tableInfoTextView.text = "Current table: $tableName"

        holder.tableInfoTextView.visibility = View.GONE
        if ( holder.tableInfoTextView.text != "Current table: Reading"  ) {
            // 隱藏題目
            holder.questionText.visibility = View.VISIBLE
            holder.questionText.text = "No: ${currentQuestion.number}"

            // 隱藏選項文字
            holder.option1Button.text = "1"
            holder.option2Button.text = "2"
            holder.option3Button.text = "3"

            // 其他你已經有的程式碼...
            if ( holder.tableInfoTextView.text == "Current table: Listen_1") {
                val questionNumber = currentQuestion.number
                val optionNumber = 1 // 假設這是選項1
                val imageName = "listen1_${questionNumber}_$optionNumber"
                val resourceId = holder.itemView.context.resources.getIdentifier(
                    imageName,
                    "drawable",
                    holder.itemView.context.packageName
                )

                if (resourceId != 0) {
                    val drawable = ContextCompat.getDrawable(holder.itemView.context, resourceId)
                    holder.option1Button.setCompoundDrawablesWithIntrinsicBounds(
                        drawable,
                        null,
                        null,
                        null
                    )
                    holder.option1Button.text = "1" // 設置文字為空字串
                }

                val optionNumber2 = 2 // 假設這是選項2
                val imageName2 = "listen1_${questionNumber}_$optionNumber2"
                val resourceId2 = holder.itemView.context.resources.getIdentifier(
                    imageName2,
                    "drawable",
                    holder.itemView.context.packageName
                )

                if (resourceId2 != 0) {
                    val drawable2 = ContextCompat.getDrawable(holder.itemView.context, resourceId2)
                    holder.option2Button.setCompoundDrawablesWithIntrinsicBounds(
                        drawable2,
                        null,
                        null,
                        null
                    )
                    holder.option2Button.text = "2" // 設置文字為空字串
                }

                val optionNumber3 = 3 // 假設這是選項3
                val imageName3 = "listen1_${questionNumber}_$optionNumber3"
                val resourceId3 = holder.itemView.context.resources.getIdentifier(
                    imageName3,
                    "drawable",
                    holder.itemView.context.packageName
                )

                if (resourceId3 != 0) {
                    val drawable3 = ContextCompat.getDrawable(holder.itemView.context, resourceId3)
                    holder.option3Button.setCompoundDrawablesWithIntrinsicBounds(
                        drawable3,
                        null,
                        null,
                        null
                    )
                    holder.option3Button.text = "3" // 設置文字為空字串
                }
            }
            else {
                val questionNumber = currentQuestion.number
                val optionNumber = 1 // 假設這是選項1
                val imageName = "listen2_${questionNumber}_$optionNumber"
                val resourceId = holder.itemView.context.resources.getIdentifier(
                    imageName,
                    "drawable",
                    holder.itemView.context.packageName
                )

                if (resourceId != 0) {
                    val drawable = ContextCompat.getDrawable(holder.itemView.context, resourceId)
                    holder.option1Button.setCompoundDrawablesWithIntrinsicBounds(
                        drawable,
                        null,
                        null,
                        null
                    )
                    holder.option1Button.text = "1" // 設置文字為空字串
                }

                val optionNumber2 = 2 // 假設這是選項2
                val imageName2 = "listen2_${questionNumber}_$optionNumber2"
                val resourceId2 = holder.itemView.context.resources.getIdentifier(
                    imageName2,
                    "drawable",
                    holder.itemView.context.packageName
                )

                if (resourceId2 != 0) {
                    val drawable2 = ContextCompat.getDrawable(holder.itemView.context, resourceId2)
                    holder.option2Button.setCompoundDrawablesWithIntrinsicBounds(
                        drawable2,
                        null,
                        null,
                        null
                    )
                    holder.option2Button.text = "2" // 設置文字為空字串
                }

                val optionNumber3 = 3 // 假設這是選項3
                val imageName3 = "listen2_${questionNumber}_$optionNumber3"
                val resourceId3 = holder.itemView.context.resources.getIdentifier(
                    imageName3,
                    "drawable",
                    holder.itemView.context.packageName
                )

                if (resourceId3 != 0) {
                    val drawable3 =
                        ContextCompat.getDrawable(holder.itemView.context, resourceId3)
                    holder.option3Button.setCompoundDrawablesWithIntrinsicBounds(
                        drawable3,
                        null,
                        null,
                        null
                    )
                    holder.option3Button.text = "3" // 設置文字為空字串
                }
            }
        }

        /* val optionNumber0 = 0 // 假設這是選項3
         val imageName0 = "listen1_${questionNumber}_$optionNumber0"
         val resourceId0 = holder.itemView.context.resources.getIdentifier(imageName0, "drawable", holder.itemView.context.packageName)

         if (resourceId0 != 0) {
             holder.questionImage.setImageResource(resourceId0)
         }*/

        else {
            holder.questionText.text = "No: ${currentQuestion.number}\n${currentQuestion.questionText}"
            holder.option1Button.text = currentQuestion.option1
            holder.option2Button.text = currentQuestion.option2
            holder.option3Button.text = currentQuestion.option3
            //updateButtonState(holder, currentQuestion)
            val questionNumber = currentQuestion.number
            val optionNumber = 1// 這裡假設這是選項1
            /*val imageName = "listen1_${questionNumber}_$optionNumber"
            val resourceId = holder.itemView.context.resources.getIdentifier(imageName, "drawable", holder.itemView.context.packageName)

            if (resourceId != 0) {
                val drawable = ContextCompat.getDrawable(holder.itemView.context, resourceId)
                holder.option1Button.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            }*/

            val optionNumber2 = 2// 這裡假設這是選項1
           /* val imageName2 = "listen1_${questionNumber}_$optionNumber2"
            val resourceId2 = holder.itemView.context.resources.getIdentifier(imageName2, "drawable", holder.itemView.context.packageName)

            if (resourceId2 != 0) {
                val drawable = ContextCompat.getDrawable(holder.itemView.context, resourceId2)
                holder.option2Button.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            }*/

            val optionNumber3 = 3// 這裡假設這是選項1
            /*val imageName3 = "listen1_${questionNumber}_$optionNumber3"
            val resourceId3 = holder.itemView.context.resources.getIdentifier(imageName3, "drawable", holder.itemView.context.packageName)

            if (resourceId3 != 0) {
                val drawable = ContextCompat.getDrawable(holder.itemView.context, resourceId3)
                holder.option3Button.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            }*/
            // 清除圖片和選項
            holder.option1Button.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            holder.option2Button.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            holder.option3Button.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
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
        if ( holder.tableInfoTextView.text != "Current table: Reading" ) {
            holder.playButton.setOnClickListener {
                val questionNumber = currentQuestion.number // 取得當前題目的編號

                //val audioFileName = "Lis01/${String.format("%02d", questionNumber)}--1.wav" // 動態建構音頻檔案路徑
                var audioFileName = "Lis01/01--${String.format("%d", questionNumber)}.wav" // 動態建構音頻檔案路徑
                if ( holder.tableInfoTextView.text == "Current table: Listen_1" ) {
                    var tmp = "Lis01/01--${String.format("%d", questionNumber)}.wav" // 動態建構音頻檔案路徑
                    audioFileName = tmp
                }
                else if ( holder.tableInfoTextView.text == "Current table: Listen_2" ) {
                    var tmp = "Lis02/02--${String.format("%d", questionNumber)}.wav" // 動態建構音頻檔案路徑
                    audioFileName = tmp
                }
                try {
                    val assetManager = holder.itemView.context.assets
                    val assetFileDescriptor = assetManager.openFd(audioFileName)
                    val mediaPlayer = MediaPlayer()

                    mediaPlayer.setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
                    mediaPlayer.prepare()
                    mediaPlayer.start()


                    // 在播放完成後釋放 MediaPlayer 資源
                    mediaPlayer.setOnCompletionListener { player ->
                        player.release()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    // 處理錯誤
                }
            }
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
                //notifyItemChanged(position)
            } else if (previousSelectedOption == answerText) {
                correctAnswers--
                //notifyItemChanged(position)
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