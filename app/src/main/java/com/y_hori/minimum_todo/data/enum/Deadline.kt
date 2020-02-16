package com.y_hori.minimum_todo.data.enum

enum class Deadline(val seconds: Long, val title: String) {
    ONE_MINUTE(60, "1分後"),
    ONE_HOUR(60 * 60, "1時間後"),
    ONE_DAY(60 * 60 * 24, "1日後"),
    ONE_WEEK(60 * 60 * 24 * 7, "1週間後");

    companion object {
        fun listOfTitle() = values().map { it.title }
    }
}
