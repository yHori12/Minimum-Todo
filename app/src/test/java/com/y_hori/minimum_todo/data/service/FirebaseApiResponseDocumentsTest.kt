package com.y_hori.minimum_todo.data.service

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.y_hori.minimum_todo.data.model.Task
import com.y_hori.minimum_todo.data.repository.toDocumentId
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class FirebaseApiResponseDocumentsTest {

    private lateinit var testData: FirebaseApiResponseDocuments

    private val dummy1 = Task("XprfShQEIXwqJ8QmwCJb", "テストデータ１", "詳細1", 1581836279, false)
    private val dummy2 = Task("yMjyphzJPmb9RtK3xDnt", "テストデータ2", "詳細2", 1581836270, true)

    private lateinit var context: Context

    @Before
    fun setUp() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter = moshi.adapter(FirebaseApiResponseDocuments::class.java)
        val dummyJson = "{\n" +
                "  \"documents\": [\n" +
                "    {\n" +
                "      \"name\": \"projects/fir-cloudstorerealtimeexam/databases/(default)/documents/NuZbfs8fqENWjkIZzByabwn65Ec2/XprfShQEIXwqJ8QmwCJb\",\n" +
                "      \"fields\": {\n" +
                "        \"description\": {\n" +
                "          \"stringValue\": \"詳細1\"\n" +
                "        },\n" +
                "        \"due_date\": {\n" +
                "          \"integerValue\": \"1581836279\"\n" +
                "        },\n" +
                "        \"is_completed\": {\n" +
                "          \"booleanValue\": false\n" +
                "        },\n" +
                "        \"title\": {\n" +
                "          \"stringValue\": \"テストデータ１\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"createTime\": \"2020-02-16T06:57:00.796456Z\",\n" +
                "      \"updateTime\": \"2020-02-16T06:57:00.796456Z\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"projects/fir-cloudstorerealtimeexam/databases/(default)/documents/NuZbfs8fqENWjkIZzByabwn65Ec2/yMjyphzJPmb9RtK3xDnt\",\n" +
                "      \"fields\": {\n" +
                "        \"description\": {\n" +
                "          \"stringValue\": \"詳細2\"\n" +
                "        },\n" +
                "        \"due_date\": {\n" +
                "          \"integerValue\": \"1581836270\"\n" +
                "        },\n" +
                "        \"is_completed\": {\n" +
                "          \"booleanValue\": true\n" +
                "        },\n" +
                "        \"title\": {\n" +
                "          \"stringValue\": \"テストデータ2\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"createTime\": \"2020-02-16T06:56:51.586141Z\",\n" +
                "      \"updateTime\": \"2020-02-16T06:56:51.586141Z\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n"

        testData = jsonAdapter.fromJson(dummyJson)!!
    }

    @Test
    fun jsonParse_isCorrect() {
        val testDocuments = testData.documents!!.map { document ->
            Task(
                id = document.name.toDocumentId() ?: "",
                title = document.fields.title.stringValue,
                dueDate = document.fields.dueDate.integerValue,
                description = document.fields.description.stringValue,
                isCompleted = document.fields.isCompleted.booleanValue
            )
        }.toMutableList()

        assertEquals(testDocuments[0], dummy1)
        assertEquals(testDocuments[1], dummy2)
    }
}
