package com.de4aber.cappsule

import android.graphics.BlendMode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.de4aber.cappsule.User.FriendListFragment
import kotlinx.android.synthetic.main.activity_friend.*

class FriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        supportFragmentManager.beginTransaction()
            .add(R.id.friendScroll, FriendListFragment()).commit()
    }
}