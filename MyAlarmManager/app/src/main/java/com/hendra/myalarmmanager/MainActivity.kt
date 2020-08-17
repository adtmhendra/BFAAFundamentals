package com.hendra.myalarmmanager

import android.app.AlarmManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Time
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    private lateinit var alarmReceiver: AlarmReceiver

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOnceDate.setOnClickListener(this)
        btnOnceTime.setOnClickListener(this)
        btnSetOneTimeAlarm.setOnClickListener(this)
        btnRepeatTime.setOnClickListener(this)
        btnSetRepeatAlarm.setOnClickListener(this)
        btnCancelRepeatingAlarm.setOnClickListener(this)

        alarmReceiver = AlarmReceiver()

    }

    override fun onClick(v: View) {

        when(v.id) {
            R.id.btnOnceDate -> {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }
            R.id.btnOnceTime -> {
                val timePickerFragment = TimePickerFragment()
                timePickerFragment.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
            }

            // memanggil metode setOneTimeAlarm dari AlarmReceiver
            R.id.btnSetOneTimeAlarm -> {
                val onceDate = tvOnceDate.text.toString()
                val onceTime = tvOnceTime.text.toString()
                val onceMessage = edtOnceMessage.text.toString()

                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, onceDate, onceTime, onceMessage)
            }

            R.id.btnRepeatTime -> {
                val timePickerFragment = TimePickerFragment()
                timePickerFragment.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG)
            }

            R.id.btnSetRepeatAlarm -> {
                val repeatTime = tvRepeatTime.text.toString()
                val repeatMessage = edtRepeatMessage.text.toString()

                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, repeatTime, repeatMessage)
            }

            R.id.btnCancelRepeatingAlarm -> alarmReceiver.cancelAlarm(this, AlarmReceiver.TYPE_REPEATING)
        }

    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {

        // Siapkan date formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Set text dari textview once
        tvOnceDate.text = df.format(calendar.time)

    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {

        // Siapkan time formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val df = SimpleDateFormat("HH:mm", Locale.getDefault())

        // Set text dari textview berdasarkan tag
        when(tag) {
            TIME_PICKER_ONCE_TAG -> tvOnceTime.text = df.format(calendar.time)
            else -> tvRepeatTime.text = df.format(calendar.time)
        }

    }


}