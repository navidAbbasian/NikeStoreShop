package com.explain.nikestore.data.repo.source

import com.explain.nikestore.data.MessageResponse
import com.explain.nikestore.data.TokenResponse
import io.reactivex.Single

interface UserDataSource {

    fun login(username: String, password: String): Single<TokenResponse>
    fun signUp(username: String, password: String): Single<MessageResponse>
    fun loadToken()
    fun saveToken(token: String, refreshToken: String)
    fun saveUsername(username: String)
    fun getUsername(): String
    fun signOut()
}