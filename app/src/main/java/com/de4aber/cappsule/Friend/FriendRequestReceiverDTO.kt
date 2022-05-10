package com.de4aber.cappsule.Friend

import org.json.JSONObject

class FriendRequestReceiverDTO (val id:Int, val UserRequesting: String) {

    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["friendshipId"] as Int,
                jsonObject["fromUser"] as String,)

}