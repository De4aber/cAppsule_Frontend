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
        passInfo.setOnClickListener { onClickPasswordInfo() }
    }

    private fun onClickLogin() {
        finish()
    }

    private fun onClickPasswordInfo() {
        Toast.makeText(applicationContext, R.string.passwordReqs, Toast.LENGTH_LONG).show()
    }

    private fun onClickSignUp() {

        if (!isLegalUsername()){
            return
        }

        if (!isLegalPassword()){
            return
        }
    }

    /**
     * Checks if the user provided passwords is identical, and checks if the password is secure enough
     * (8+ characters, special character, lowercase character, uppercase character, and a number)
     * and diplays a message to the user if not approved
     *
     * @return returns true if password is approved
     */
    private fun isLegalPassword(): Boolean{
        val pw1 = editTextTextPassword.text.toString()
        val pw2 = editTextTextPassword2.text.toString()

        //Check if the two password is identical
        if(pw1 != pw2){
            Toast.makeText(applicationContext, R.string.passwordNoMatch, Toast.LENGTH_SHORT).show()
            return false
        }
        //Check for password length is 8 or above
        if(pw1.length < 8){
            Toast.makeText(applicationContext, R.string.passwordUnder8, Toast.LENGTH_SHORT).show()
            return false
        }
        //Check for special characters
        if(!checkForPattern(pw1,"[^a-zA-Z0-9]")){
            Toast.makeText(applicationContext, R.string.passwordNoSpecialCharacters, Toast.LENGTH_SHORT).show()
            return false
        }
        //Check for lowercase characters
        if (!checkForPattern(pw1,"[a-z]")){
            Toast.makeText(applicationContext, R.string.passwordNoLowerCase, Toast.LENGTH_SHORT).show()
            return false
        }
        //Check for uppercase characters
        if (!checkForPattern(pw1,"[A-Z]")) {
            Toast.makeText(applicationContext, R.string.passwordNoUpperCase, Toast.LENGTH_SHORT).show()
            return false
        }
        //Check for numbers
        if(!checkForPattern(pw1,"[0-9]")){
            Toast.makeText(applicationContext, R.string.passwordNoNumbers, Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    /**
     * Checks if the user-provided username is 4 or more characters
     * and displays a message to the user if not approved
     *
     * @return returns true if username is approved
     */
    private fun isLegalUsername(): Boolean{
        val username = editTextTextPersonName.text.toString()
        if(username.length < 4){
            Toast.makeText(applicationContext, R.string.usernameTooShort, Toast.LENGTH_SHORT).show()
            return false
        }else return true
    }

    /**
     * Checks if the Regex pattern is in the string given
     *
     * @param text the string given
     * @param pattern Regex to check for
     * @return returns false if no match
     */
    private fun checkForPattern(text: String, pattern: String): Boolean {
        val pattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher = pattern.matcher(text)
        return matcher.find()
    }
}