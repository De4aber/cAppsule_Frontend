package com.de4aber.cappsule.User

import org.json.JSONObject

class BEUser(val id:Int, val name:String) {

    constructor(username:String): this(-1,username)


    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["id"] as Int,
                jsonObject["username"] as String)

    override fun toString(): String {
        return "$id, $name"
    }

}