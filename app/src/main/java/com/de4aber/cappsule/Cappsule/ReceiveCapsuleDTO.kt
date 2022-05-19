package com.de4aber.cappsule.Cappsule

import org.json.JSONObject


class ReceiveCapsuleDTO(val capsuleId:Int, val senderUsername: String, val message: String?, val time: String?, val latitude: String, val longitude: String, val photo: String?) {

    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["capsuleId"] as Int,
                jsonObject["senderUsername"] as String,
                jsonObject["message"] as String?,
                jsonObject["time"] as String?,
                jsonObject["latitude"].toString(),
                jsonObject["longitude"].toString(),
                jsonObject["photo"].toString())



}