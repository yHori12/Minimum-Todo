package com.y_hori.minimum_todo.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.y_hori.minimum_todo.data.model.User

class SplashViewModel() : ViewModel() {

    private var splashRepository: SplashRepository = SplashRepository()

    fun getUserIfAuthenticated():User? {
        return splashRepository.getUserIfAuthenticated()
    }

}
