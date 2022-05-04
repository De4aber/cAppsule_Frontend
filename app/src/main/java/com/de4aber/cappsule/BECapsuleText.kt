package com.de4aber.cappsule

import java.io.Serializable

class BECapsuleText(var receiver: List<BEFriend>) : Serializable{
    var message: String = ""
    var time: String = ""
    var date: String = ""
    var location: String = ""

    override fun toString(): String {
        return "Message = $message, Time = $time, Date = $date, Location = $location, Receiver = $receiver"
    }

}
