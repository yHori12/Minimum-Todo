package com.y_hori.minimum_todo.worker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.testing.TestListenableWorkerBuilder
import com.example.robin.roomwordsample.Utils.NotificationWorker
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

//WorkManagerの起動テスト
class NotificationWorkerTest {
    private lateinit var context: Context
    private lateinit var workManager: WorkManager

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        workManager = WorkManager.getInstance(context)
    }

    @Test
    fun doWork() {
        val worker = TestListenableWorkerBuilder<NotificationWorker>(context).build()
        val result = worker.startWork().get()
        assertThat(result, CoreMatchers.`is`(ListenableWorker.Result.success()))
    }
}
