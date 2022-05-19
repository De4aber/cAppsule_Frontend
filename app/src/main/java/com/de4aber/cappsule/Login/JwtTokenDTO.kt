package com.de4aber.cappsule.Login

import org.json.JSONObject

class JwtTokenDTO(val jwt: String?, val message: String, val userId:Int) {

    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["jwt"] as String,
                jsonObject["message"] as String,
                jsonObject["userId"] as Int,
                )

}