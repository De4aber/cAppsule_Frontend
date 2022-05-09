package com.de4aber.cappsule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_location3.*

class AddLocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location3)
        btnSaveLocation.setOnClickListener { Save() }
        btnLocationBack.setOnClickListener { Back() }
    }

    private fun Back() {
        finish()
    }

    private fun Save() {
        TODO("Not yet implemented")
    }
}