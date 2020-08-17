package com.hendra.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class DetailCategoryFragment : Fragment(), View.OnClickListener {
    lateinit var tvCategoryName: TextView
    lateinit var tvCategoryDescription: TextView
    lateinit var btnProfile: Button
    lateinit var btnShowDialog: Button
    var description: String? = null

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_DESC = "extra_desc"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Detail Category Fragment"

        tvCategoryName        = view.findViewById(R.id.tv_category_name)
        tvCategoryDescription = view.findViewById(R.id.tv_category_description)
        btnProfile            = view.findViewById(R.id.btn_profile)
        btnProfile.setOnClickListener(this)
        btnShowDialog         = view.findViewById(R.id.btn_show_dialog)
        btnShowDialog.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            val getDesc = savedInstanceState.getString(EXTRA_DESC)
            description = getDesc
        }

        if (arguments != null) {
            val getName = arguments?.getString(EXTRA_NAME)
            tvCategoryName.text = getName
            tvCategoryDescription.text = description
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_profile) {
            val mIntent = Intent(activity, ProfileActivity::class.java)
            startActivity(mIntent)
        } else if (v.id == R.id.btn_show_dialog) {
            val mOptionDialogFragment = OptionDialogFragment()
            val mChildFragmentManager = childFragmentManager
            mOptionDialogFragment.show(mChildFragmentManager, OptionDialogFragment::class.java.simpleName)
        }
    }

    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object : OptionDialogFragment.OnOptionDialogListener {
        override fun onOptionChosen(text: String?) {
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }
    }
}