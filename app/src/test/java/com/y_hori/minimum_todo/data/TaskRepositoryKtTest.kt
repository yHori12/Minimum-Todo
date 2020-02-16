package com.y_hori.minimum_todo.data

import com.y_hori.minimum_todo.data.repository.toDocumentId
import org.junit.Assert.*
import org.junit.Test

class TaskRepositoryKtTest {

    //String?.toDocumentId()のテスト
    @Test
    fun parse_isCorrect() {
        assertEquals("0Q3N9DXKXlR6DdTi6BET", "projects/fir-cloudstorerealtimeexam/databases/(default)/documents/TpVipln98wPEs0qpUbUWihsTG083/0Q3N9DXKXlR6DdTi6BET".toDocumentId())
    }

}
