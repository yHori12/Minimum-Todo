package com.y_hori.minimum_todo.data.repository

import  com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashRepository {

    companion object {
        private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    }

    fun getUidIfAuthenticated(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

}
