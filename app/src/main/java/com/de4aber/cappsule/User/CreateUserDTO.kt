package com.de4aber.cappsule.User

class CreateUserDTO(val username:String, val birthdate: String, val password:String) {


    override fun toString(): String {

        return "$username, $birthdate, $password"
    }



}