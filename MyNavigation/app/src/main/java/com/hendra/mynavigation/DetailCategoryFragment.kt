package com.hendra.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_detail_category.*

class DetailCategoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // receive data in navigation with SafeArg
        tvName.text        = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).name
        tvDescription.text = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).desc.toString()

        btnToHomeFragment.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_detailCategoryFragment_to_homeFragment)
        )
    }
}