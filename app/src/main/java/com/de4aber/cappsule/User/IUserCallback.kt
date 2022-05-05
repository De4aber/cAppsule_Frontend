package com.de4aber.cappsule.User

interface IUserCallback {

    fun onUsersReady(users: List<UserDTO>)
}