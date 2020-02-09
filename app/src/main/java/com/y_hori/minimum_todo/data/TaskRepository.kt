package com.y_hori.minimum_todo.data

import com.y_hori.minimum_todo.data.model.Task
import retrofit2.Response
import java.io.IOException

class TaskRepository: BaseRepository() {

    companion object {
        private var instance: TaskRepository? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: TaskRepository().also { instance = it }
            }
    }

    private fun dummy(): MutableList<Task> {
        return mutableListOf(
            Task(id = 1),
            Task(id = 2),
            Task(id = 3)
        )
    }

    fun getTasks(): MutableList<Task> {
        return dummy()
    }

}


open class BaseRepository {
    suspend fun <T : Any> apiOutput(
        call: suspend () -> Response<T>,
        error: String
    ): NetworkResult<T> {
        val response = call.invoke()
        return if (response.isSuccessful)
            NetworkResult.Success(response.body()!!)
        else
            NetworkResult.Error(IOException(error))
    }
}

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val output: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}
