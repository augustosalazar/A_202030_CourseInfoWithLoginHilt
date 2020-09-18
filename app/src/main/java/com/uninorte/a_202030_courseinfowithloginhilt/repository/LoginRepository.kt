package com.uninorte.a_202030_courseinfowithlogin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uninorte.a_202030_courseinfowithlogin.service.api.login.LoginApiService
import com.uninorte.a_202030_courseinfowithlogin.model.User
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val service: LoginApiService
) {
    var userLiveData = MutableLiveData<User>()

    fun signIn(user: User) = service.signIn(user)

    fun signUp(user: User) = service.signUp(user)

    fun getUser() = userLiveData as LiveData<User>

}