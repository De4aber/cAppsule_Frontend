package com.de4aber.cappsule.Login

import org.json.JSONObject

class LoginDTO (val username: String, val password: String) {

    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["username"] as String,
                jsonObject["password"] as String,)

}