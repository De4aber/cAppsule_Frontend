package com.de4aber.cappsule

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.de4aber.cappsule.Utility.SecurityHelper
import com.scottyab.aescrypt.AESCrypt
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception
import java.security.GeneralSecurityException


class MainActivity : AppCompatActivity() {
    var securityHelper: SecurityHelper = SecurityHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignUp.setOnClickListener{onClickSignUp()}
        btnLogin.setOnClickListener { onClickLogin() }
    }

    private fun onClickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent);
    }

    private fun onClickLogin() {
        val plainPW = editTextTextPassword.text.toString()
        val username = editTextTextPersonName.text.toString()
        val key = securityHelper.getEncryptionKey()
        try {
            val encryptedPassword = AESCrypt.encrypt(key, plainPW)
            Log.d("TAG", encryptedPassword)
        }        catch (e: GeneralSecurityException){
            throw Exception("Key is most-likely not generated \n $e")
        }
    }
}