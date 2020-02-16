package com.y_hori.minimum_todo.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.robin.roomwordsample.Utils.NotificationWorker
import com.y_hori.minimum_todo.data.enum.Deadline
import com.y_hori.minimum_todo.data.model.Task
import com.y_hori.minimum_todo.data.model.User
import com.y_hori.minimum_todo.data.repository.TaskRepository
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainViewModel(private val repository: TaskRepository, private val app: Application) :
    AndroidViewModel(app) {

    private val _tasks = MutableLiveData<MutableList<Task>>()
    val tasks: LiveData<MutableList<Task>>
        get() = _tasks

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _description = MutableLiveData<String>("")
    val description: LiveData<String>
        get() = _description

    private val _liveEventShowErrorDialog = MutableLiveData<Boolean>(false)
    val liveEventShowErrorDialog: LiveData<Boolean>
        get() = _liveEventShowErrorDialog

    private val _liveEventCreateTaskSuccess = MutableLiveData<Boolean>(false)
    val liveEventCreateTaskSuccess: LiveData<Boolean>
        get() = _liveEventCreateTaskSuccess

    private val _shouldShowProgress = MutableLiveData<Boolean>(false)
    val shouldShowProgress: LiveData<Boolean>
        get() = _shouldShowProgress

    private var user: User = User()

    fun fetchTasks() {
        viewModelScope.launch {
            _shouldShowProgress.value = true
            repository.fetchTasks(user)?.let { tasks ->
                _tasks.postValue(tasks)
            }
            _shouldShowProgress.value = false
        }
    }

    fun createTask(selectedDeadline: Deadline?) {
        if (title.value.isNullOrEmpty()) {
            _liveEventShowErrorDialog.value = true
            _liveEventShowErrorDialog.value = false
            return
        }

        viewModelScope.launch {
            _shouldShowProgress.value = true

            val task = Task(
                title = title.value!!,
                description = description.value!!,
                dueDate = when {
                    selectedDeadline != null -> selectedDeadline.seconds + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
                    else -> 0L
                }
            )
            if (task.dueDate != 0L) {
                createNotificationWorker(task)
            }

            repository.createTask(
                task,
                user
            )?.let { responsedTask ->
                _tasks.value?.add(responsedTask)
//                _tasks.postValue(_tasks.value?.add(responsedTask))
                _liveEventCreateTaskSuccess.value = true
                _liveEventCreateTaskSuccess.value = false
                _shouldShowProgress.value = false
            }
        }
    }

    private fun createNotificationWorker(task: Task) {
        val data = Data.Builder()
            .putString(NotificationWorker.KEY_INPUTDATA_TITLE,task.title)
            .putString(NotificationWorker.KEY_INPUTDATA_DESCRIPTION, task.description).build()

        val notifyManager = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInputData(data)
            .setInitialDelay(
                task.dueDate - TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()),
                TimeUnit.SECONDS
            )
            .addTag(task.dueDate.toString())
            .build()
        WorkManager.getInstance(app.applicationContext).enqueue(notifyManager)
    }

    fun updateTitle(title: String) {
        _title.value = title
    }

    fun updateDescription(description: String) {
        _description.value = description
    }

    fun saveUser(user: User) {
        this.user = user
    }

    fun completeTask(task: Task) {
        viewModelScope.launch {
            _shouldShowProgress.value = true
            repository.completeTask(task, user)?.let { tasks ->
                _tasks.postValue(tasks)
                _liveEventShowErrorDialog.value = true
                _liveEventShowErrorDialog.value = false
                _shouldShowProgress.value = false
            }
        }
    }
}
