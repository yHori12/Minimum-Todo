package com.y_hori.minimum_todo.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.y_hori.minimum_todo.data.repository.SplashRepository
import com.y_hori.minimum_todo.data.repository.TaskRepository

@Suppress("UNCHECKED_CAST")
class SplashViewModelFactory(
    private val repository: SplashRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SplashViewModel(repository) as T
}
