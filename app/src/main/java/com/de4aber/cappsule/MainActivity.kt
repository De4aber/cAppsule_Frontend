package com.de4aber.cappsule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.de4aber.cappsule.User.UserActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnSignUp: Button = findViewById(R.id.btnSignUp)

        btnWhoops.setOnClickListener{onClickLogin()}
        btnSignUp.setOnClickListener{onClickSignUp()}
    }

    private fun onClickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent);
    }

    private fun onClickLogin(){
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent);
    }
}