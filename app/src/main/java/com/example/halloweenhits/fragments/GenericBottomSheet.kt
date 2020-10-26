package com.example.halloweenhits.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.halloweenhits.Constants
import com.example.halloweenhits.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GenericBottomSheet: BottomSheetDialogFragment() {

    companion object {
        fun getInstance(title: String = "", imageUrl : String = ""): GenericBottomSheet {
            return GenericBottomSheet().apply {
                this.titleText = title
                this.imageUrl = imageUrl
            }
        }
    }
    var titleText = ""
    var imageUrl = ""

    var title: TextView? = null; var close: TextView? = null
    var imageView: AppCompatImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.layout_generic_bottom_sheet,container, false)
        title = view.findViewById(R.id.title_tv)
        close = view.findViewById(R.id.close)
        close?.setOnClickListener {
            this.dismissAllowingStateLoss()
        }
        imageView = view.findViewById(R.id.container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(context).load(Constants.IMAGE_BASE_URL + imageUrl).into(imageView)
    }
}