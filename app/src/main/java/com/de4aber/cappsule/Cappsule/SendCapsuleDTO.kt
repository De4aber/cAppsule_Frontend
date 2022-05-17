package com.de4aber.cappsule.Cappsule

class SendCapsuleDTO(val recieverUserName: String) {

    var message: String = ""
    var time: String = ""
    var date: String = ""
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var pictureUri : String = ""

    var isText: Boolean = true
    var isTimeNDate: Boolean = true

    constructor(recieverUserName: String, message: String, time:String, date: String) : this(recieverUserName) {
        this.message = message
        this.time = time
        this.date = date
    }

    override fun toString(): String {
        if(isText && isTimeNDate){
            return "TO $recieverUserName, '$message', ON $date AT $time"
        }


        return "SendCapsuleDTO(recieverUserName='$recieverUserName', message='$message', time='$time', date='$date', latitude=$latitude, longitude=$longitude, pictureUri='$pictureUri', isText=$isText, isTimeNDate=$isTimeNDate)"
    }


}