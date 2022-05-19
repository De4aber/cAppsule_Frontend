package com.de4aber.cappsule

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.Login.LoginDTO
import com.de4aber.cappsule.User.UserActivity
import com.de4aber.cappsule.Utility.SecurityHelper
import com.scottyab.aescrypt.AESCrypt
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception
import java.security.GeneralSecurityException


class LoginActivity : AppCompatActivity() {
    var securityHelper: SecurityHelper = SecurityHelper()

    private val loginViewModel : LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }


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
        val plainPW = editTextTextPassword.text.toString()
        val username = editTextTextPersonName.text.toString()

        loginViewModel.login(this, LoginDTO(username, plainPW))
            .observe(this){jwt->
                Toast.makeText(this, "Jwt" + jwt.message, Toast.LENGTH_SHORT).show()
                if(jwt.jwt.isNullOrEmpty() || jwt.jwt.startsWith("null")){

                }
                else{
                    val intent = MainActivity.newIntent(this, jwt.userId)
                    startActivity(intent);
                }
            }

        /*
        val key = securityHelper.getEncryptionKey()
        try {
            val encryptedPassword = AESCrypt.encrypt(key, plainPW)
            Log.d("TAG", "ENCRYPTED PASSWORD: $encryptedPassword")
        }        catch (e: GeneralSecurityException){
            throw Exception("Key is most-likely not generated \n $e")
        }
         */


    }
}