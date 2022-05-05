package com.de4aber.cappsule.User

import org.json.JSONObject

class UserDTO(val id:Int, val username:String, val birthdate: String, val CapScore:Int) {

    constructor(username:String, birthdate: String): this(-1,username, birthdate, 0)


    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["id"] as Int,
                jsonObject["username"] as String,
                jsonObject["birthDate"] as String,
            jsonObject["capScore"] as Int)

    override fun toString(): String {

        return "$id, $username, $birthdate, $CapScore"
    }



}