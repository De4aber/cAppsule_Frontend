package com.de4aber.cappsule.User

import androidx.lifecycle.ViewModel

class LoggedUserViewModel(user: UserDTO):ViewModel() {

    val loggedUser: UserDTO = user

}