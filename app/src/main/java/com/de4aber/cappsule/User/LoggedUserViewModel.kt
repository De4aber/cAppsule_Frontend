package com.de4aber.cappsule.User

import android.util.Log
import android.util.Log.DEBUG
import androidx.lifecycle.ViewModel
import com.de4aber.cappsule.Friend.FriendRepository
import java.lang.Exception
import java.lang.NullPointerException
private const val TAG = "LoggedUserViewModel"
class LoggedUserViewModel():ViewModel() {

    lateinit var loggedUser: UserDTO
    val friendRepository = FriendRepository();
    val userRepo = UserRepo()



}