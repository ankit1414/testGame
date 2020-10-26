package com.example.halloweenhits.Models

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("title")
    var movieName: String = "",
    @SerializedName("backdrop_path")
    var movieImageUrl: String = ""
) {
    companion object {
        //make this function better later
        fun makeJumbledWord(answer: StringBuilder): StringBuilder? {
            val jumbledAnswer = StringBuilder()
            for (i in 0 until answer.length) {
                if (i % 2 != 0) {
                    jumbledAnswer.append(answer[i])
                }
            }
            for (i in 0 until answer.length) {
                if (i % 2 == 0) {
                    jumbledAnswer.append(answer[i])
                }
            }
            return jumbledAnswer
        }
    }
}