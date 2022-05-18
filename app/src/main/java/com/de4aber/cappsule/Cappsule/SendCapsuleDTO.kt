package com.de4aber.cappsule.Cappsule

class SendCapsuleDTO(val senderId: Int, val recieverUserName: String) {

    var message: String? = null
    var time: String? = null
    var date: String? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var photo : ByteArray? = null

    var isText: Boolean = true
    var isTimeNDate: Boolean = true

    constructor(senderId: Int,recieverUserName: String, message: String, time:String, date: String) : this(senderId, recieverUserName) {
        this.message = message
        this.time = time
        this.date = date
    }

    override fun toString(): String {
        if(isText && isTimeNDate){
            return "TO $recieverUserName, '$message', ON $date AT $time"
        }


        return "SendCapsuleDTO(recieverUserName='$recieverUserName', message='$message', time='$time', date='$date', latitude=$latitude, longitude=$longitude,  isText=$isText, isTimeNDate=$isTimeNDate)"
    }


}