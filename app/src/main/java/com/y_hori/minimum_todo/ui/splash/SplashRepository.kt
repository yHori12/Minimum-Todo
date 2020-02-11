package com.y_hori.minimum_todo.ui.splash

import com.google.firebase.auth.FirebaseAuth
import com.y_hori.minimum_todo.data.model.User

class SplashRepository {

    companion object {
        private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    }

    fun getUserIfAuthenticated(): User? {
        return firebaseAuth.currentUser?.let { user ->
            User(user.uid)
        }
    }

}
