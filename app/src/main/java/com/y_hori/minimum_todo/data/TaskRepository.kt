package com.y_hori.minimum_todo.data

import com.y_hori.minimum_todo.data.model.Task
import com.y_hori.minimum_todo.data.service.TaskApiInterface
import com.y_hori.minimum_todo.data.service.TaskApiService
import retrofit2.Response
import java.io.IOException

class TaskRepository(private val apiInterface: TaskApiInterface): BaseRepository() {

    companion object {
        private var instance: TaskRepository? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: TaskRepository(TaskApiService.taskApi).also { instance = it }
            }
    }

    private fun dummy(): MutableList<Task> {
        return mutableListOf(
            Task(id = 1,title = "title1"),
            Task(id = 2,title = "title2"),
            Task(id = 3,title = "title3")
        )
    }

    fun getTasks(uid: String): MutableList<Task> {
//        apiInterface.fetchTasks(uid)
        return dummy()
    }

    fun postTask() {
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
