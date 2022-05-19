package com.de4aber.cappsule

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.de4aber.cappsule.Friend.FriendRepository
import com.de4aber.cappsule.Login.JwtTokenDTO
import com.de4aber.cappsule.Login.LoginDTO
import com.de4aber.cappsule.Login.LoginRepository
import com.de4aber.cappsule.User.UserDTO
import com.de4aber.cappsule.User.UserListFragment
import com.de4aber.cappsule.User.UserRepo

const val TAG = "LoginViewModel"
class LoginViewModel:ViewModel() {



    private val userRepo = UserRepo();
    private val loginRepository = LoginRepository()

    fun getAllUsers(): LiveData<List<UserDTO>> {
        return userRepo.getAll()
    }

    fun login(context: Context, loginDTO: LoginDTO): MutableLiveData<JwtTokenDTO> {
        return loginRepository.login(context, loginDTO)
    }



    init {
        Log.d(TAG, "ViewModel instance created")

    }

}