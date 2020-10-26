package com.example.halloweenhits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.halloweenhits.Constants
import com.example.halloweenhits.Models.MovieModel
import com.example.halloweenhits.R


class ArrangeFragment: BaseFragment() {
    companion object {
        fun getInstance(movieModel: MovieModel?): ArrangeFragment {
            return ArrangeFragment().apply {
                this.movieData = movieModel
            }
        }
    }

    private var movieData: MovieModel? = null
    private var otv1: TextView? = null; private  var otv2:TextView? = null; private  var otv3:TextView? = null; private  var otv4:TextView? = null; private  var otv5:TextView? = null; private  var otv6:TextView? = null
    private var ttv1: TextView? = null; private  var ttv2:TextView? = null; private  var ttv3:TextView? = null; private  var ttv4:TextView? = null; private  var ttv5:TextView? = null; private  var ttv6:TextView? = null
    private var ptv1: TextView? = null; private  var ptv2:TextView? = null; private  var ptv3:TextView? = null; private  var ptv4:TextView? = null; private  var ptv5:TextView? = null; private  var ptv6:TextView? = null
    private var qtv1: TextView? = null; private  var qtv2:TextView? = null; private  var qtv3:TextView? = null; private  var qtv4:TextView? = null; private  var qtv5:TextView? = null; private  var qtv6:TextView? = null

    private var ohtv1: TextView? = null; private  var ohtv2:TextView? = null; private  var ohtv3:TextView? = null; private  var ohtv4:TextView? = null; private  var ohtv5:TextView? = null; private  var ohtv6:TextView? = null
    private var thtv1: TextView? = null; private  var thtv2:TextView? = null; private  var thtv3:TextView? = null; private  var thtv4:TextView? = null; private  var thtv5:TextView? = null; private  var thtv6:TextView? = null
    private var phtv1: TextView? = null; private  var phtv2:TextView? = null; private  var phtv3:TextView? = null; private  var phtv4:TextView? = null; private  var phtv5:TextView? = null; private  var phtv6:TextView? = null
    private var qhtv1: TextView? = null; private  var qhtv2:TextView? = null; private  var qhtv3:TextView? = null; private  var qhtv4:TextView? = null; private  var qhtv5:TextView? = null; private  var qhtv6:TextView? = null


    private var reset: Button? = null; private  var done:Button? = null; private var seeImage:Button? = null

    private var ansTextviews: ArrayList<TextView>? = null
    private var hintTextviews: ArrayList<TextView>? = null
    var index = 0
    var score = 0

    private var inputStack: ArrayList<String>? = null

    var layout: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.layout_arrange_fragment, container, false)
        allFindViewByIds()
        makeArraylists()
        setQuizData()
        done!!.setOnClickListener {
            val correctAns: String = getSpaceHandledString(movieData?.movieName ?: "")
            if (inputStack!!.size == correctAns.length) {
                val submitedString = inputStackToString()
                if (submitedString.equals(correctAns, ignoreCase = true)) {
                    score++
                    showToast("correct")
                    listener?.increaseScore()
                }
                listener?.increaseAttemptedQuestions()
                listener?.showNextQuestion()
            }
        }
        seeImage?.setOnClickListener {
            GenericBottomSheet.getInstance(Constants.MOVIE_IMAGE, movieData?.movieImageUrl ?: "").show(childFragmentManager,"TAG")
        }
        reset!!.setOnClickListener {
            showToast("reset")
            resetQuiz()
            setQuizData()
        }
        return layout
    }
    private fun inputStackToString(): String? {
        val sb = StringBuilder()
        for (i in inputStack!!.indices) {
            sb.append(inputStack!![i])
        }
        return sb.toString()
    }
    private fun resetQuiz() {
        //do clean previous settings
        // essential
        //clear the inputstack arraylist
        inputStack!!.clear()
        for (j in ansTextviews!!.indices) {
            val ansInstance = ansTextviews!![j]
            ansInstance.text = ""
            ansInstance.visibility = TextView.GONE
            ansInstance.setBackgroundResource(R.drawable.quiz_blanks)
            hintTextviews!![j].text = ""
            hintTextviews!![j].visibility = TextView.GONE
        }
    }

    private fun getSpaceHandledString(input: String): String {
        return input.replace(" ","_")
    }

    private fun setQuizData() {
        val thisString: String = MovieModel.Companion.makeJumbledWord(StringBuilder(getSpaceHandledString(movieData?.movieName ?: ""))).toString()
        for (j in 0 until thisString.length) {
            val temp = java.lang.StringBuilder()
            temp.append(thisString[j])
            val p = temp.toString()
            hintTextviews!![j].text = p
            hintTextviews!![j].visibility = TextView.VISIBLE
            ansTextviews!![j].visibility = TextView.VISIBLE
            attachOnClicks(hintTextviews!![j])
        }
        //Toast.makeText(Quiz_activity.this , "jumbled ="+instance.getJumbled().toString(),Toast.LENGTH_SHORT).show();
    }

    private fun attachOnClicks(temp: TextView) {
        temp.setOnClickListener {
            if (!temp.text.toString().isEmpty()) {
                inputStack!!.add(temp.text.toString())
                temp.text = ""
                updateAnswerTiles()
            }
        }
    }

    private fun updateAnswerTiles() {
        for (i in ansTextviews!!.indices) {
            ansTextviews!![i].text = ""
            ansTextviews!![i].setBackgroundResource(R.drawable.quiz_blanks)
        }
        for (i in inputStack!!.indices) {
            ansTextviews!![i].text = inputStack!![i]
            ansTextviews!![i].setBackgroundResource(R.drawable.quiz_options_tvs)
        }
    }

    private fun makeArraylists() {
        //ans
        ansTextviews = ArrayList()
        ansTextviews?.add(otv1!!)
        ansTextviews?.add(otv2!!)
        ansTextviews?.add(otv3!!)
        ansTextviews?.add(otv4!!)
        ansTextviews?.add(otv5!!)
        ansTextviews?.add(otv6!!)
        ansTextviews?.add(ttv1!!)
        ansTextviews?.add(ttv2!!)
        ansTextviews?.add(ttv3!!)
        ansTextviews?.add(ttv4!!)
        ansTextviews?.add(ttv5!!)
        ansTextviews?.add(ttv6!!)
        ansTextviews?.add(ptv1!!)
        ansTextviews?.add(ptv2!!)
        ansTextviews?.add(ptv3!!)
        ansTextviews?.add(ptv4!!)
        ansTextviews?.add(ptv5!!)
        ansTextviews?.add(ptv6!!)

        ansTextviews?.add(qtv1!!)
        ansTextviews?.add(qtv2!!)
        ansTextviews?.add(qtv3!!)
        ansTextviews?.add(qtv4!!)
        ansTextviews?.add(qtv5!!)
        ansTextviews?.add(qtv6!!)

        //hint
        hintTextviews = ArrayList()
        hintTextviews?.add(ohtv1!!)
        hintTextviews?.add(ohtv2!!)
        hintTextviews?.add(ohtv3!!)
        hintTextviews?.add(ohtv4!!)
        hintTextviews?.add(ohtv5!!)
        hintTextviews?.add(ohtv6!!)
        hintTextviews?.add(thtv1!!)
        hintTextviews?.add(thtv2!!)
        hintTextviews?.add(thtv3!!)
        hintTextviews?.add(thtv4!!)
        hintTextviews?.add(thtv5!!)
        hintTextviews?.add(thtv6!!)
        hintTextviews?.add(phtv1!!)
        hintTextviews?.add(phtv2!!)
        hintTextviews?.add(phtv3!!)
        hintTextviews?.add(phtv4!!)
        hintTextviews?.add(phtv5!!)
        hintTextviews?.add(phtv6!!)

        hintTextviews?.add(qhtv1!!)
        hintTextviews?.add(qhtv2!!)
        hintTextviews?.add(qhtv3!!)
        hintTextviews?.add(qhtv4!!)
        hintTextviews?.add(qhtv5!!)
        hintTextviews?.add(qhtv6!!)
    }


    private fun allFindViewByIds() {
        inputStack = ArrayList()
        //answers tvs all
        layout?.apply {
            otv1 = findViewById(R.id.ans_tv1)
            otv2 = findViewById(R.id.ans_tv2)
            otv3 = findViewById(R.id.ans_tv3)
            otv4 = findViewById(R.id.ans_tv4)
            otv5 = findViewById(R.id.ans_tv5)
            otv6 = findViewById(R.id.ans_tv6)
            ttv1 = findViewById(R.id.ans_tv7)
            ttv2 = findViewById(R.id.ans_tv8)
            ttv3 = findViewById(R.id.ans_tv9)
            ttv4 = findViewById(R.id.ans_tv10)
            ttv5 = findViewById(R.id.ans_tv11)
            ttv6 = findViewById(R.id.ans_tv12)
            ptv1 = findViewById(R.id.ans_tv13)
            ptv2 = findViewById(R.id.ans_tv14)
            ptv3 = findViewById(R.id.ans_tv15)
            ptv4 = findViewById(R.id.ans_tv16)
            ptv5 = findViewById(R.id.ans_tv17)
            ptv6 = findViewById(R.id.ans_tv18)
            qtv1 = findViewById(R.id.ans_tv19)
            qtv2 = findViewById(R.id.ans_tv20)
            qtv3 = findViewById(R.id.ans_tv21)
            qtv4 = findViewById(R.id.ans_tv22)
            qtv5 = findViewById(R.id.ans_tv23)
            qtv6 = findViewById(R.id.ans_tv24)

            //hints tvs all
            ohtv1 = findViewById(R.id.op1)
            ohtv2 = findViewById(R.id.op2)
            ohtv3 = findViewById(R.id.op3)
            ohtv4 = findViewById(R.id.op4)
            ohtv5 = findViewById(R.id.op5)
            ohtv6 = findViewById(R.id.op6)
            thtv1 = findViewById(R.id.op7)
            thtv2 = findViewById(R.id.op8)
            thtv3 = findViewById(R.id.op9)
            thtv4 = findViewById(R.id.op10)
            thtv5 = findViewById(R.id.op11)
            thtv6 = findViewById(R.id.op12)
            phtv1 = findViewById(R.id.op13)
            phtv2 = findViewById(R.id.op14)
            phtv3 = findViewById(R.id.op15)
            phtv4 = findViewById(R.id.op16)
            phtv5 = findViewById(R.id.op17)
            phtv6 = findViewById(R.id.op18)

            qhtv1 = findViewById(R.id.op19)
            qhtv2 = findViewById(R.id.op20)
            qhtv3 = findViewById(R.id.op21)
            qhtv4 = findViewById(R.id.op22)
            qhtv5 = findViewById(R.id.op23)
            qhtv6 = findViewById(R.id.op24)

            //buttons
            reset = findViewById(R.id.reset_button)
            done = findViewById(R.id.done_button)
            seeImage = findViewById(R.id.see_image)
        }

    }

}