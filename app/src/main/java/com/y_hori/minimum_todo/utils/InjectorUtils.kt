package com.y_hori.minimum_todo.utils

import com.y_hori.minimum_todo.MinimumTodoApplication
import com.y_hori.minimum_todo.data.repository.SplashRepository
import com.y_hori.minimum_todo.data.repository.TaskRepository
import com.y_hori.minimum_todo.viewmodels.MainViewModelFactory
import com.y_hori.minimum_todo.viewmodels.SplashViewModel
import com.y_hori.minimum_todo.viewmodels.SplashViewModelFactory

object InjectorUtils {
    fun provideMainViewModelFactory(): MainViewModelFactory {
        val repository =
            getTaskRepository()
        val application = MinimumTodoApplication.context
        return MainViewModelFactory(
            repository, application
        )
    }

    private fun getTaskRepository(): TaskRepository {
        return TaskRepository.getInstance()
    }

    fun provideSplashViewModelFactory(): SplashViewModelFactory {
        return SplashViewModelFactory(SplashRepository())
    }
}
