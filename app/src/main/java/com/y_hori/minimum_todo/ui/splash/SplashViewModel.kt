package com.y_hori.minimum_todo.ui.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser

class SplashViewModel() : ViewModel() {

    private var splashRepository: SplashRepository = SplashRepository()

    fun getUidIfAuthenticated(): FirebaseUser? {
        return splashRepository.getUidIfAuthenticated()
    }

}
