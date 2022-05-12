package com.de4aber.cappsule.User

import org.json.JSONObject

class SearchForUserIsFriendDTO (val username: String,val isFriend: Boolean) {

    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["username"] as String,
                jsonObject["isFriends"] as Boolean)
}