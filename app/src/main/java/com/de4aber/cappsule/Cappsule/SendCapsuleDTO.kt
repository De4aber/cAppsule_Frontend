package com.de4aber.cappsule.Cappsule

import android.graphics.Bitmap

class SendCapsuleDTO(val senderId: Int, val recieverUserName: String) {

    var message: String? = null
    var time: String? = null
    var date: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var photo : Bitmap? = null

    override fun toString(): String {
        return "recieverUserName='$recieverUserName', message='$message', time='$time', date='$date', latitude=$latitude, longitude=$longitude"
    }


}