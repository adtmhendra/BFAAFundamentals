package com.hendra.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // creating action bar title in fragment
    fun setTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = "Home Fragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()

        val btnCategory: Button = view.findViewById(R.id.btn_category)
        btnCategory.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        // code for move to category fragment
        if (v.id == R.id.btn_category) {
            val mFragmentManager = fragmentManager
            val mCategoryFragment = CategoryFragment()

            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, mCategoryFragment, CategoryFragment::class.java.simpleName)
                addToBackStack(null) // this fragment (HomeFragment) added to stack
                commit()
            }
        }
    }
}