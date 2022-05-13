package com.de4aber.cappsule.Utility

import java.io.Serializable

class BECapsuleText(var receiver: List<BEFriend>) : Serializable{
    var message: String = ""
    var time: String = ""
    var date: String = ""
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var pictureUri : String = ""
    var cappsuleID: Int = 0

    override fun toString(): String {
        return "Message = $message, Time = $time, Date = $date, Location = $latitude.$longitude, Picture Uri = $pictureUri, Receiver = $receiver"
    }

}
