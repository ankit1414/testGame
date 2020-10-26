package com.example.halloweenhits

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.halloweenhits.Models.MovieModel
import com.example.halloweenhits.fragments.ImageFragment

class MainActivity : BaseActivity(), FragmentListener {

    var fragmentContainer: FrameLayout? = null
    var mVm: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById(R.id.container)
        mVm = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        showNextQuestion()
    }

    override fun showNextQuestion() {
        if((mVm?.questionCount ?: 0) >= Constants.TOTAL_QUES) {
            showResultFragment()
            return
        }
        fragmentContainer?.let {
            if(mVm?.movieArray?.size != null && mVm?.movieArray?.size!! > 1) {
                 val movie = getMovie()
                 this.supportFragmentManager?.beginTransaction().replace(it.id, ImageFragment.getInstance(Constants.INTRO_MSG, movie)).commitAllowingStateLoss()

            } else {
                // error fragment...
            }
        }
    }
    private fun getMovie() : MovieModel {
        val randomNum = (0..(mVm?.movieArray?.size ?: 0)).random()
        mVm?.movieArray?.get(randomNum)?.let{
            if(it.movieName.length >= 25){
                for(i in 0 until (mVm?.movieArray?.size ?: 0)) {
                    if((mVm?.movieArray?.get(i)?.movieName?.length ?: 25) <= 24){
                        return mVm?.movieArray?.get(i) ?: MovieModel("","")
                    }
                }
            } else {
                return it
            }
        }
        return MovieModel("","")

    }
    private fun showResultFragment() {
        fragmentContainer?.let {
            this.supportFragmentManager?.beginTransaction().replace(it.id, ImageFragment.getInstance("Score", MovieModel("", Constants.SCORE_IMAGE2))).commitAllowingStateLoss()
        }
    }
    override fun replaceFragmentWith(fragment: Fragment) {
        if((mVm?.questionCount ?: 0) >= Constants.TOTAL_QUES) {
            showResultFragment()
            return
        }
        fragmentContainer?.let {
            this.supportFragmentManager?.beginTransaction().replace(it.id, fragment).commitAllowingStateLoss()
        }
    }

    override fun increaseScore() {
        mVm?.let { it.score++ }
    }

    override fun increaseAttemptedQuestions() {
        mVm?.let { it.questionCount++ }
    }

    override fun getScore(): Int {
        return mVm?.score ?: 0
    }
}
interface FragmentListener {
    fun replaceFragmentWith(fragment: Fragment)
    fun increaseScore()
    fun increaseAttemptedQuestions()
    fun showNextQuestion()
    fun getScore(): Int
}