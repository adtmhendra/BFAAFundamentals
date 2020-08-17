package com.hendra.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CategoryFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // creating action bar title in fragment
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Category Fragment"

        val btn_detail_category: Button = view.findViewById(R.id.btn_detail_category)
        btn_detail_category.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        // code for move to detail category fragment
        if (v.id == R.id.btn_detail_category) {
            val mFragmentManager = fragmentManager
            val mDetailCategoryFragment = DetailCategoryFragment()

            val mBundle = Bundle()
            mBundle.putString(DetailCategoryFragment.EXTRA_NAME, "This script was sent from Category Fragment using Bundle")
            val description = "This script was sent from Category Fragment using Getter and Setter"

            mDetailCategoryFragment.arguments   = mBundle
            mDetailCategoryFragment.description = description

            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container, mDetailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}