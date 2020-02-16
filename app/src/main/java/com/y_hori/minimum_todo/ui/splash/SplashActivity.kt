package com.y_hori.minimum_todo.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser
import com.y_hori.minimum_todo.R
import com.y_hori.minimum_todo.data.model.User
import com.y_hori.minimum_todo.ui.main.MainActivity
import com.y_hori.minimum_todo.utils.InjectorUtils
import com.y_hori.minimum_todo.viewmodels.SplashViewModel

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 100
        const val INTENT_KEY_USER = "USER"
    }

    private val splashViewModel: SplashViewModel by viewModels {
        InjectorUtils.provideSplashViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToNextScreen()
    }

    private fun goToNextScreen() {
        splashViewModel.getUidIfAuthenticated()?.let { user ->
            goToMainActivity(user)
        } ?: goToFirebaseAuthActivity()
    }

    private fun goToFirebaseAuthActivity() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_done_all_24px)
                .setTheme(R.style.AppTheme)
                .build(),
            RC_SIGN_IN
        )
    }

    private fun goToMainActivity(firebaseUser: FirebaseUser) {
        firebaseUser.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.token?.let { token ->
                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        intent.putExtra(
                            INTENT_KEY_USER,
                            User(token = token, uid = firebaseUser.uid)
                        )
                        startActivity(intent)
                        finish()
                    }
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            //認証済みなら0が返る
            goToNextScreen()
        }
    }

}
