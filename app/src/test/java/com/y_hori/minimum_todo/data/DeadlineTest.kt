package com.y_hori.minimum_todo.data

import com.y_hori.minimum_todo.data.enum.Deadline
import com.y_hori.minimum_todo.data.enum.Deadline.*
import org.junit.Assert.*
import org.junit.Test

class DeadlineTest {

    //タイトル一覧を表示する関数のテスト
    @Test
    fun listOfTitle_isCorrect() {
        assertEquals(listOf(ONE_MINUTE.title,ONE_HOUR.title,ONE_DAY.title,ONE_WEEK.title),Deadline.listOfTitle())
    }
}
