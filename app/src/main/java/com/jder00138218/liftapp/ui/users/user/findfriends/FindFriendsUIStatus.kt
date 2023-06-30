package com.jder00138218.liftapp.ui.users.user.findfriends

import java.lang.Exception

sealed class FindFriendsUIStatus{
    object Resume : FindFriendsUIStatus()
    class Error(val exception: Exception) : FindFriendsUIStatus()
    data class ErrorWithMessage(val message: String) : FindFriendsUIStatus()
    data class Success(val token: String) : FindFriendsUIStatus()
}
