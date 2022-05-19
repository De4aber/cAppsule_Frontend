package com.de4aber.cappsule.User

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.util.Log.DEBUG
import android.widget.Toast
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


    var latitudeNewCapsule: Double? = null
    var longitudeNewCapsule: Double? = null
    var photoNewCapsule : Bitmap? = null
    var messageNewCapsule : String? = null
    var timeNewCapsule: String? = null
    var dateNewCapsule: String? = null

    var isTextNewCapsule:Boolean = true
    var isTimeNewCapsule:Boolean = true
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

        if(recipientsNewCapsule.isNotEmpty()){
            for (f in recipientsNewCapsule){
                val cap = SendCapsuleDTO(loggedUser.id,f.username)

                if(isTextNewCapsule){
                    cap.message = messageNewCapsule
                }
                else{
                    Toast.makeText(context,"Sending photos is not implemented", Toast.LENGTH_SHORT).show()
                    cap.photo = null
                    return capsules
                }

                if(isTimeNewCapsule){
                    cap.time = timeNewCapsule
                    cap.date = dateNewCapsule
                }
                else{
                    cap.latitude = latitudeNewCapsule
                    cap.longitude = longitudeNewCapsule
                }

                Log.d(TAG, cap.toString())
                capsules.add(capsuleRepository.sendCapsule(context, cap))
            }
        }
        else{
            Toast.makeText(context,"You need to choose at least one recipient", Toast.LENGTH_SHORT).show()
        }
        return capsules
    }

    fun receiveCapsules(): LiveData<List<Capsule>> {
        return capsuleRepository.getCapsulesByReceiverId(loggedUser.id)
    }

    fun getCapsuleById(id: Int): LiveData<Capsule> {
        return capsuleRepository.getCapsuleById(id)
    }

}