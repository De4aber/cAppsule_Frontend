package com.de4aber.cappsule

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.de4aber.cappsule.Friendlist.FriendListFragment
import com.de4aber.cappsule.Friendlist.FriendSegmentFragment
import com.de4aber.cappsule.Home.HomeSegmentFragment
import com.de4aber.cappsule.User.LoggedUserViewModel
import com.de4aber.cappsule.User.UserDTO
import com.de4aber.cappsule.User.UserRepo
import kotlinx.android.synthetic.main.activity_main.*

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
                    .add(R.id.fragShowing, HomeSegmentFragment.newInstance()).commit()

                home.setOnClickListener { onClickHome() }
                map.setOnClickListener { onClickMap() }
                friends.setOnClickListener { onClickFriend() }
                cappsule.setOnClickListener { onClickCapsule() }
            }
        }, userId)
    }

    private fun onClickCapsule() {
        home.setColorFilter(Color.rgb(87, 124, 140))
        map.setColorFilter(Color.rgb(87, 124, 140))
        friends.setColorFilter(Color.rgb(87, 124, 140))
        cappsule.setColorFilter(Color.rgb(100, 198, 192))
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragShowing, CapsuleSegmentFragment.newInstance())
            .commit()
    }

    private fun onClickMap() {
        home.setColorFilter(Color.rgb(87, 124, 140))
        map.setColorFilter(Color.rgb(100, 198, 192))
        friends.setColorFilter(Color.rgb(87, 124, 140))
        cappsule.setColorFilter(Color.rgb(87, 124, 140))
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragShowing, MapSegmentFragment.newInstance())
            .commit()
    }



    private fun onClickFriend() {
        home.setColorFilter(Color.rgb(87, 124, 140))
        map.setColorFilter(Color.rgb(87, 124, 140))
        friends.setColorFilter(Color.rgb(100, 198, 192))
        cappsule.setColorFilter(Color.rgb(87, 124, 140))
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragShowing, FriendSegmentFragment.newInstance())
            .commit()
    }

    private fun onClickHome() {
        home.setColorFilter(Color.rgb(100, 198, 192))
        map.setColorFilter(Color.rgb(87, 124, 140))
        friends.setColorFilter(Color.rgb(87, 124, 140))
        cappsule.setColorFilter(Color.rgb(87, 124, 140))
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragShowing, HomeSegmentFragment.newInstance())
            .commit()
    }


    companion object{
        fun newIntent(packageContext: Context, loggedUserId: Int): Intent {
            return Intent(packageContext, MainActivity::class.java).apply {
                putExtra(EXTRA_USERID, loggedUserId)
            }
        }
    }
}