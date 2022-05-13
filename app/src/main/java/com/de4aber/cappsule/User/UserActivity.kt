package com.de4aber.cappsule.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.LoginViewModel
import com.de4aber.cappsule.R

class UserActivity : AppCompatActivity() {

    private val loginViewModel : LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentFriendList, UserListFragment()).commit()
    }
}