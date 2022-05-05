package com.de4aber.cappsule.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.de4aber.cappsule.R

class UserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentFriendList, UserListFragment()).commit()
    }
}