package com.de4aber.cappsule

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.de4aber.cappsule.User.UserActivity
import com.de4aber.cappsule.Utility.SecurityHelper
import com.scottyab.aescrypt.AESCrypt
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception
import java.security.GeneralSecurityException


class LoginActivity : AppCompatActivity() {
    var securityHelper: SecurityHelper = SecurityHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnSignUp.setOnClickListener{onClickSignUp()}
        btnLogin.setOnClickListener { onClickLogin() }
        und.setOnClickListener{onClickUndskyld()}

        window.decorView.apply {systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) }
    }

    private fun onClickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent);
    }

    private fun onClickUndskyld(){
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent);
    }

    private fun onClickLogin() {

        val intent = Intent(this, CreateCapsuleActivity::class.java)
        startActivity(intent);
        val plainPW = editTextTextPassword.text.toString()
        val username = editTextTextPersonName.text.toString()
        val key = securityHelper.getEncryptionKey()
        try {
            val encryptedPassword = AESCrypt.encrypt(key, plainPW)
            Log.d("TAG", "ENCRYPTED PASSWORD: $encryptedPassword")
        }        catch (e: GeneralSecurityException){
            throw Exception("Key is most-likely not generated \n $e")
        }


    }
}