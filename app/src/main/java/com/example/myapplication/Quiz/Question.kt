package com.example.myapplication.Quiz

data class Question(
    val number: Int,
    val questionText: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val answer: String, // 将答案改为字符串类型
    val hasPic: Int,
    var selectedOption: String = ""
) {
    fun getAnswerText(): String {
        return when (answer) {
            "1" -> option1
            "2" -> option2
            "3" -> option3
            else -> ""
        }
    }
}