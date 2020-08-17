package com.hendra.kalkulatorsederhana

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_move.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val REQUEST = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculatorMenu: Button = findViewById(R.id.btnCalculatorMenu)
        btnCalculatorMenu.setOnClickListener(this)

        val btnMoveActivityWithData: Button = findViewById(R.id.btnMoveActivityWithData)
        btnMoveActivityWithData.setOnClickListener(this)

        val btnDialNumber: Button = findViewById(R.id.btnDialNumber)
        btnDialNumber.setOnClickListener(this)

        val btnMoveActivityWithObject: Button = findViewById(R.id.btnMoveActivityWithObject)
        btnMoveActivityWithObject.setOnClickListener(this)

        val btnMoveActivityForResult: Button = findViewById(R.id.btnMoveActivityForResult)
        btnMoveActivityForResult.setOnClickListener(this)

        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.title = "Menu Utama"
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnCalculatorMenu -> {
                val moveActivity = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveActivity)
            }

            R.id.btnMoveActivityWithData -> {
                val moveActivityWithData = Intent(this@MainActivity, MoveActivityWithData::class.java)
                moveActivityWithData.putExtra(MoveActivityWithData.EXTRA_NAME, "Hendra")
                moveActivityWithData.putExtra(MoveActivityWithData.EXTRA_AGE, 20)
                startActivity(moveActivityWithData)
            }

            R.id.btnDialNumber -> {
                val phoneNumber = "087780944373"
                val dialNumber  = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialNumber)
            }

            R.id.btnMoveActivityWithObject -> {
                val persons: ArrayList<Person> = arrayListOf(Person(
                    "Hendra Pratik Aditama",
                    20,
                    "Jl. Swadaya 1",
                    "Jakarta"
                ), Person(
                    "Yudha",
                    26,
                    "Jl. Mawar 2",
                    "Depok"
                ), Person(
                    "Dimas",
                    17,
                    "Jl. Kamboja 3",
                    "Bandung"
                ))

                val moveDataWithObjectArray = Intent(this@MainActivity, MoveActivityWithObject::class.java)
                moveDataWithObjectArray.putParcelableArrayListExtra(MoveActivityWithObject.EXTRA_PERSON, persons)
                startActivity(moveDataWithObjectArray)

//                val person = Person(
//                    "Hendra Pratik Aditama",
//                    20,
//                    "Jl. Swadaya 1",
//                    "Kota Depok"
//                )
//
//                val intentParcelable = Intent(this@MainActivity, MoveActivityWithObject::class.java)
//                intentParcelable.putExtra(MoveActivityWithObject.EXTRA_PERSON, person)
//                startActivity(intentParcelable)
            }

            R.id.btnMoveActivityForResult -> {
                val moveActivityForResult = Intent(this@MainActivity, MoveForResultActivity::class.java)
                startActivityForResult(moveActivityForResult, REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST) {
            if (resultCode == MoveForResultActivity.RESULT) {
                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                val text = "Anda menyukai angka $selectedValue"
                tv_result.text = text
            }
        }
    }
}