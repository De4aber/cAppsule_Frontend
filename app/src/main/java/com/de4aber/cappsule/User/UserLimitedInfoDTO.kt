package com.de4aber.cappsule.User

import org.json.JSONObject

class UserLimitedInfoDTO(val username:String, val birthdate: String, val CapScore:Int) {

    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["username"] as String,
                jsonObject["birthDate"] as String,
            jsonObject["capScore"] as Int)

    override fun toString(): String {

        return "$username, $birthdate, $CapScore"
    }



}