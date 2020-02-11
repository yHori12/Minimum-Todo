package com.y_hori.minimum_todo.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.y_hori.minimum_todo.data.model.User
import com.y_hori.minimum_todo.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 100
        const val INTENT_KEY_USER = "USER"
    }
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashViewModel()
        goToNextScreen()
    }

    private fun initSplashViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }


    private fun goToNextScreen() {
        splashViewModel.getUserIfAuthenticated()?.let { user ->
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
                .build(),
            RC_SIGN_IN
        )
    }

    private fun goToMainActivity(user: User) {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra(INTENT_KEY_USER, user)
        startActivity(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            //認証済みなら0が返る
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser ?: return
                goToMainActivity(User(user.uid))
                finish()

                //todo エラーハンドリング
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

}
