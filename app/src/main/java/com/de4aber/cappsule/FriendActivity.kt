package com.de4aber.cappsule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.de4aber.cappsule.Friendlist.FriendListFragment
import com.de4aber.cappsule.User.UserDTO

class FriendActivity : AppCompatActivity() {

    var user: UserDTO? =null;

    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        supportFragmentManager.beginTransaction()
            .add(R.id.friendScroll, FriendListFragment()).commit()
    }

    companion object{
        fun newIntent(packageContext: Context, loggedUser: UserDTO): Intent{
            val friendActivity = FriendActivity()
            friendActivity.user = loggedUser
            return Intent(packageContext, friendActivity::class.java)
        }
    }
}