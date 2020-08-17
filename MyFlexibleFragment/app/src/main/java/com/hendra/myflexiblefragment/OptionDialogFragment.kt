package com.hendra.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class OptionDialogFragment : DialogFragment(), View.OnClickListener {
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbMun: RadioButton
    private lateinit var rbLiv: RadioButton
    private lateinit var rbChe: RadioButton
    private lateinit var rbMci: RadioButton
    private lateinit var btnClose: Button
    private lateinit var btnChoose: Button
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment

        if (fragment is DetailCategoryFragment) {
            val detailCategoryFragment = fragment
            this.optionDialogListener = detailCategoryFragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rgOptions = view.findViewById(R.id.rg_options)
        rbMun = view.findViewById(R.id.rb_mun)
        rbLiv = view.findViewById(R.id.rb_liv)
        rbChe = view.findViewById(R.id.rb_che)
        rbMci = view.findViewById(R.id.rb_mci)
        btnClose = view.findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnChoose.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_close) {
            dialog?.cancel()
        } else if (v.id == R.id.btn_choose) {
            if (rgOptions.checkedRadioButtonId != 0) {
                var coach: String? = null
                when (rgOptions.checkedRadioButtonId) {
                    R.id.rb_mun -> coach = rbMun.text.toString()
                    R.id.rb_liv -> coach = rbLiv.text.toString()
                    R.id.rb_che -> coach = rbChe.text.toString()
                    R.id.rb_mci -> coach = rbMci.text.toString()
                }
                if (optionDialogListener != null) {
                    optionDialogListener?.onOptionChosen(coach)
                }
                dialog?.dismiss()
            }
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}