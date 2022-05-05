package com.de4aber.cappsule.User

interface ICallback {

    fun onUsersReady(users: List<UserDTO>)
}