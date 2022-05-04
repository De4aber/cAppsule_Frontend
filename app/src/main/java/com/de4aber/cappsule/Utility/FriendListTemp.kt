package com.de4aber.cappsule.Utility

import java.io.Serializable

class FriendListTemp : Serializable{
    val allFriends = mutableListOf<BEFriend>(
        BEFriend("bo"),
        BEFriend("bent"),
        BEFriend("benny"),
        BEFriend("birk"),
        BEFriend("bjarne"),
        BEFriend("bjørn"),
        BEFriend("bjarke"),
        BEFriend("benjamin")
    )

    fun getAll(): MutableList<BEFriend> {
        return allFriends
    }
}