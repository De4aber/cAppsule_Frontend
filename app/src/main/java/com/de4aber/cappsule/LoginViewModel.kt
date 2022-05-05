package com.de4aber.cappsule

import androidx.lifecycle.ViewModel
import com.de4aber.cappsule.Friend.FriendRepository
import com.de4aber.cappsule.User.UserRepo

class LoginViewModel:ViewModel() {

    val userRepo = UserRepo();
    val friendRepository = FriendRepository();


}