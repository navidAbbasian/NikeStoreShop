package com.explain.nikestore.feature.profile

import com.explain.nikestore.common.NikeViewModel
import com.explain.nikestore.data.TokenContainer
import com.explain.nikestore.data.repo.UserRepository

class ProfileViewModel(private val userRepository: UserRepository) : NikeViewModel() {
    val username: String
        get() = userRepository.getUserName()

    val isSignedIn: Boolean
        get() = TokenContainer.token != null

    fun signOut() = userRepository.signOut()
}