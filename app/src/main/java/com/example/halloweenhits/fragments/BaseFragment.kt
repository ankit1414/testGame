package com.example.halloweenhits.fragments

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.halloweenhits.FragmentListener

open class BaseFragment: Fragment() {

    var listener: FragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener)
            listener = context as FragmentListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}