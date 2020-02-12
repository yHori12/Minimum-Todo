package com.y_hori.minimum_todo.data

import android.util.Log
import com.y_hori.minimum_todo.data.model.Task
import com.y_hori.minimum_todo.data.model.UserItem
import com.y_hori.minimum_todo.data.service.TaskApiInterface
import com.y_hori.minimum_todo.data.service.TaskApiService
import retrofit2.Response
import java.io.IOException

class TaskRepository(private val apiInterface: TaskApiInterface) : BaseRepository() {

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
            Task(id = 1, title = "title1"),
            Task(id = 2, title = "title2"),
            Task(id = 3, title = "title3")
        )
    }

    suspend fun getTasks(uid: String): MutableList<Task> {

        val result = apiOutput(
            call = { apiInterface.fetchTasks(uid) },
            error = "calling fetchUserList failed"
        )
        var output: MutableList<UserItem>? = null

        when (result) {
            is NetworkResult.Success ->
                output = result.output
            is NetworkResult.Error ->
                Log.d("Error", "${result.exception}")
        }


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
