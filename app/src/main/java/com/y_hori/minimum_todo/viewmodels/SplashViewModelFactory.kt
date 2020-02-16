package com.y_hori.minimum_todo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.y_hori.minimum_todo.data.repository.SplashRepository

@Suppress("UNCHECKED_CAST")
class SplashViewModelFactory(
    private val repository: SplashRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SplashViewModel(repository) as T
}
