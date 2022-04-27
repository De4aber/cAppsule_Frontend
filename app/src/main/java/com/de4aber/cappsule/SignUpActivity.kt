package com.de4aber.cappsule

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val btnLogin: Button = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener { onClickLogin() }
        buttonSignUpNow.setOnClickListener { onClickSignUp() }
    }

    private fun onClickLogin() {
        finish()
    }

    private fun onClickSignUp() {
        val username = editTextTextPersonName
        val pw1 = editTextTextPassword.text.toString()
        val pw2 = editTextTextPassword2.text.toString()


        if(pw1 != pw2){
            Toast.makeText(applicationContext, R.string.passwordNoMatch, Toast.LENGTH_SHORT).show()
            return
        }
        if(pw1.length < 8){
            Toast.makeText(applicationContext, R.string.passwordUnder8, Toast.LENGTH_SHORT).show()
            return
        }
        val pattern: Pattern = Pattern.compile("[^a-zA-Z0-9]")
        val matcher: Matcher = pattern.matcher(pw1)
        val isStringContainsSpecialCharacter: Boolean = matcher.find()
        if(!isStringContainsSpecialCharacter){
            Toast.makeText(applicationContext, R.string.passwordNoSpecialCharacters, Toast.LENGTH_SHORT).show()
            return
        }
    }
}