package com.de4aber.cappsule.Friend

interface IFriendRequestCallback {

    fun onFriendRequestsReady(friends: List<FriendRequestReceiverDTO>)

}