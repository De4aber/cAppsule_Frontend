package com.de4aber.cappsule.User

import android.content.Context
import android.util.Log
import android.util.Log.DEBUG
import androidx.lifecycle.*
import com.de4aber.cappsule.Friend.FriendDTO
import com.de4aber.cappsule.Friend.FriendRepository
import com.de4aber.cappsule.Friend.FriendRequestDTO
import com.de4aber.cappsule.Friend.FriendRequestReceiverDTO
import java.lang.Exception
import java.lang.NullPointerException
private const val TAG = "LoggedUserViewModel"
class LoggedUserViewModel():ViewModel() {

    lateinit var loggedUser: UserDTO
    private val friendRepository = FriendRepository();
    private val userRepo = UserRepo()
    var searchwordUser = ""

    fun getFriends(): LiveData<List<FriendDTO>> {
        return friendRepository.getFriendsByUserId(loggedUser.id)
    }

    fun getFriendRequests(): LiveData<List<FriendRequestReceiverDTO>> {
        return friendRepository.getFriendRequestByUserId(loggedUser.id)
    }

    fun acceptFriendRequest(friendshipId: Int): MutableLiveData<FriendDTO> {
        return friendRepository.acceptFriendRequest(loggedUser.id,friendshipId)
    }

    fun setUser(id: Int): LiveData<UserDTO> {
        return userRepo.getUserById(id)
    }

    fun getUsersBySearch(): MutableLiveData<List<UserLimitedInfoDTO>> {
        return userRepo.searchUsersByUsername(searchwordUser)
    }

    fun getUsersBySearchIsFriends(): MutableLiveData<List<SearchForUserIsFriendDTO>> {
        return friendRepository.getUsersIsFriends(searchwordUser, loggedUser.id)
    }

    fun sendFriendRequest(context: Context, toFriend: String): MutableLiveData<Boolean> {
        return friendRepository.requestFriendship(context, FriendRequestDTO(loggedUser.id, toFriend))
    }

}