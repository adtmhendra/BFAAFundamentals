package com.hendra.mybroadcastreceiver

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sms_receiver.*

class SmsReceiverActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_SMS_NO = "extra_sms_no"
        const val EXTRA_SMS_MESSAGE = "extra_sms_message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_receiver)

        title = getString(R.string.incoming_message)

        btnClose.setOnClickListener(this)

        val senderNo = intent.getStringExtra(EXTRA_SMS_NO)
        val senderMessage = intent.getStringExtra(EXTRA_SMS_MESSAGE)

        tvFrom.setText(senderNo)
        tvMessage.setText(senderMessage)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnClose) finish()
    }
}