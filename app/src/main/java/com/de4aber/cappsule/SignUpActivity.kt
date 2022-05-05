package com.de4aber.cappsule

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val btnLogin: Button = findViewById(R.id.btnBackToLogin);

        btnLogin.setOnClickListener { onClickLogin() }
    }

    private fun onClickLogin() {

        /*
        val user = BEUser(txtUsernameRegister.text.toString(),)

        UserRepo().createUser(user)

         */

        finish()
    }
}