package com.de4aber.cappsule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.de4aber.cappsule.Friendlist.FriendListFragment

class FriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        supportFragmentManager.beginTransaction()
            .add(R.id.friendScroll, FriendListFragment()).commit()
    }
}