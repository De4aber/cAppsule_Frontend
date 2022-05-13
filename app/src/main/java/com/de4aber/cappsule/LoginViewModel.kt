package com.de4aber.cappsule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.de4aber.cappsule.Friend.FriendRepository
import com.de4aber.cappsule.User.UserDTO
import com.de4aber.cappsule.User.UserListFragment
import com.de4aber.cappsule.User.UserRepo

const val TAG = "LoginViewModel"
class LoginViewModel:ViewModel() {



    private val userRepo = UserRepo();

    fun getUser(id:Int): LiveData<UserDTO> {
        return userRepo.getUserById(id)
    }

    fun getAllUsers(): LiveData<List<UserDTO>> {
        return userRepo.getAll()
    }



    init {
        Log.d(TAG, "ViewModel instance created")

    }

}