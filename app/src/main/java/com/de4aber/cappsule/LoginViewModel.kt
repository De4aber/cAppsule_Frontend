package com.de4aber.cappsule

import android.util.Log
import androidx.lifecycle.ViewModel
import com.de4aber.cappsule.Friend.FriendRepository
import com.de4aber.cappsule.User.UserListFragment
import com.de4aber.cappsule.User.UserRepo

const val TAG = "LoginViewModel"
class LoginViewModel:ViewModel() {



    val userRepo = UserRepo();



    init {
        Log.d(TAG, "ViewModel instance created")

    }

}