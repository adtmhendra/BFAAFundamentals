package com.hendra.mynavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // sending data in navigation with SafeArg
        btnToDetailCategoryFragment.setOnClickListener { view ->
            val toDetailCategoryFragment = CategoryFragmentDirections.actionCategoryFragmentToDetailCategoryFragment()
            toDetailCategoryFragment.name = "Hendra Pratik Aditama"
            toDetailCategoryFragment.desc = 20
            view.findNavController().navigate(toDetailCategoryFragment)
        }
    }
}