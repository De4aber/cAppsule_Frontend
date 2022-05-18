package com.de4aber.cappsule.Cappsule

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import org.json.JSONObject


class ReceiveCapsuleDTO(val senderUsername: String, val message: String?, val time: String?, val latitude: Double?, val Longitude: Double?, val photo: String?) {

    constructor(jsonObject: JSONObject) :
            this(
                jsonObject["senderUsername"] as String,
                jsonObject["message"] as String?,
                jsonObject["time"] as String?,
                jsonObject["latitude"] as Double?,
                jsonObject["longitude"] as Double?,
                jsonObject["photo"].toString())



}