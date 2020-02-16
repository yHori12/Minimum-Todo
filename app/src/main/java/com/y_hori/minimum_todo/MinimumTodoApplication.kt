package com.y_hori.minimum_todo

import android.app.Application

class MinimumTodoApplication :Application(){

    companion object {
        lateinit var context: Application
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}
