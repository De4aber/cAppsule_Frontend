package com.de4aber.cappsule.Friend

import org.json.JSONObject

class FriendDTO (val friendshipId:Int, val username:String, val CapScore:Int) {


    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["friendshipId"] as Int,
                jsonObject["username"] as String,
                jsonObject["capScore"] as Int)


}