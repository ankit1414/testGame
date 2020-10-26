package com.example.halloweenhits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.halloweenhits.Constants
import com.example.halloweenhits.Models.MovieModel
import com.example.halloweenhits.R

class ImageFragment: BaseFragment() {

    var titleText  = ""
    var movieData: MovieModel? = null

    companion object {

        fun getInstance(title: String = "", movieModel: MovieModel): ImageFragment {
            return ImageFragment().apply {
                this.titleText = title
                this.movieData = movieModel
            }
        }
    }
    var imageView: AppCompatImageView? = null
    var proceedBtn: Button? = null
    var score_tv: TextView? = null; var titleTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_image_fragment, container, false)
        imageView = view.findViewById<AppCompatImageView?>(R.id.image_view)
        titleTextView = view.findViewById<TextView?>(R.id.title_tv)
        titleTextView?.text = titleText
        score_tv = view.findViewById(R.id.score_tv)
        proceedBtn = view.findViewById(R.id.proceed_btn)
        proceedBtn?.setOnClickListener {
            listener?.replaceFragmentWith(ArrangeFragment.getInstance(movieData))
        }
        if(movieData?.movieName == "") {
            proceedBtn?.visibility = View.GONE
            score_tv?.visibility = View.VISIBLE
            score_tv?.text = "Your Score is ${listener?.getScore().toString()} out of ${Constants.TOTAL_QUES}"
            titleTextView?.textAlignment = View.TEXT_ALIGNMENT_CENTER
        } else {
            proceedBtn?.visibility = View.VISIBLE
            score_tv?.visibility = View.GONE
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(movieData?.movieName == "") {
            Glide.with(context).load(movieData?.movieImageUrl).into(imageView)
        } else
            Glide.with(context).load(Constants.IMAGE_BASE_URL + movieData?.movieImageUrl).into(imageView)
    }

}