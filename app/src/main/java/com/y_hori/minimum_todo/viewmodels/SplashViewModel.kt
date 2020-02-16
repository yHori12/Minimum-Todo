package com.y_hori.minimum_todo.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.y_hori.minimum_todo.data.repository.SplashRepository

class SplashViewModel(private val splashRepository: SplashRepository) : ViewModel() {

    fun getUidIfAuthenticated(): FirebaseUser? {
        return splashRepository.getUidIfAuthenticated()
    }

}
