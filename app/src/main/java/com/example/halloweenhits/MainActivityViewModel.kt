package com.example.halloweenhits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.halloweenhits.Models.MovieModel
import com.google.gson.Gson
import org.json.JSONObject

class MainActivityViewModel: ViewModel() {
    var questionCount = 0
    var score: Int = 0
    var movieArray: ArrayList<MovieModel> = ArrayList()

    init {
        val response = JSONObject(Constants.movieResponse)
        val movieJsonArray = response.optJSONArray("results")
        for (i in 0 until  (movieJsonArray?.length() ?: 0 )) {
            val movieJson: JSONObject? = movieJsonArray?.getJSONObject(i)
            movieJson?.let {
                movieArray.add(Gson().fromJson(it.toString(),MovieModel::class.java))
            }
        }
    }
}
class MainActivityViewModelFactory(): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainActivityViewModel() as T
}