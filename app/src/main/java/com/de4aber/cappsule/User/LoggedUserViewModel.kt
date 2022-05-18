package com.de4aber.cappsule.User

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.util.Log.DEBUG
import androidx.lifecycle.*
import com.de4aber.cappsule.Cappsule.Capsule
import com.de4aber.cappsule.Cappsule.CapsuleRepository
import com.de4aber.cappsule.Cappsule.ReceiveCapsuleDTO
import com.de4aber.cappsule.Cappsule.SendCapsuleDTO
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
    private val capsuleRepository = CapsuleRepository();
    var searchwordUser = ""


    var latitudeNewCapsule: Double = 0.0
    var longitudeNewCapsule: Double = 0.0
    var photoNewCapsule : Bitmap? = null
    var messageNewCapsule : String? = null
    var timeNewCapsule: String? = null
    var dateNewCapsule: String? = null
    val recipientsNewCapsule: MutableList<FriendDTO> = mutableListOf()


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

    fun newCapsule(context: Context): MutableList<LiveData<Boolean>> {
        val capsules = mutableListOf<LiveData<Boolean>>()

        if(messageNewCapsule != null && timeNewCapsule != null && dateNewCapsule != null && recipientsNewCapsule.isNotEmpty()){
            for (f in recipientsNewCapsule){
                val cap = SendCapsuleDTO(loggedUser.id,f.username, messageNewCapsule!!, timeNewCapsule!!, dateNewCapsule!!)
                capsules.add(capsuleRepository.sendCapsule(context, cap))
            }
        }
        return capsules
    }

    fun receiveCapsules(): LiveData<List<Capsule>> {
        return capsuleRepository.getCapsulesByReceiverId(loggedUser.id)
    }

}