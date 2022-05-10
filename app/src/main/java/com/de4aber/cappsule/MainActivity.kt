package com.de4aber.cappsule

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.Friendlist.FriendListFragment
import com.de4aber.cappsule.Friendlist.FriendSegmentFragment
import com.de4aber.cappsule.User.LoggedUserViewModel
import com.de4aber.cappsule.User.UserDTO
import com.de4aber.cappsule.User.UserRepo

private const val EXTRA_USERID = "com.de4aber.cappsule.MainActivity.user_id"

class MainActivity : AppCompatActivity() {

    var userId: Int = -1

    private val loggedUserViewModel : LoggedUserViewModel by lazy {
        ViewModelProvider(this).get(LoggedUserViewModel()::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = intent.getIntExtra(EXTRA_USERID, -1)

        loggedUserViewModel.userRepo.getUserById(object: UserRepo.IGetUserFromId{
            override fun onUserReady(user: UserDTO) {
                loggedUserViewModel.loggedUser = user
                setContentView(R.layout.activity_main)
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragShowing, FriendSegmentFragment()).commit()
            }
        }, userId)
    }

    companion object{
        fun newIntent(packageContext: Context, loggedUserId: Int): Intent {
            return Intent(packageContext, MainActivity::class.java).apply {
                putExtra(EXTRA_USERID, loggedUserId)
            }
        }
    }
}