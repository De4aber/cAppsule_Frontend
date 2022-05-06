package com.de4aber.cappsule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.Friendlist.FriendListFragment
import com.de4aber.cappsule.User.LoggedUserViewModel
import com.de4aber.cappsule.User.UserDTO
import com.de4aber.cappsule.User.UserRepo

private const val EXTRA_USERID = "com.de4aber.cappsule.user_id"

class FriendActivity : AppCompatActivity() {

    var userId: Int = -1

    private val loggedUserViewModel :LoggedUserViewModel by lazy {
        ViewModelProvider(this).get(LoggedUserViewModel()::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = intent.getIntExtra(EXTRA_USERID, -1)

        loggedUserViewModel.userRepo.getUserById(object: UserRepo.IGetUserFromId{
            override fun onUserReady(user: UserDTO) {
                loggedUserViewModel.loggedUser = user

                setContentView(R.layout.activity_friend)
                supportFragmentManager.beginTransaction()
                    .add(R.id.friendScroll, FriendListFragment()).commit()
            }
        }, userId)

    }

    companion object{
        fun newIntent(packageContext: Context, loggedUserId: Int): Intent{
            return Intent(packageContext, FriendActivity()::class.java).apply {
                putExtra(EXTRA_USERID, loggedUserId)
            }
        }
    }
}