package com.myapplication

import MainView
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainView()
        }
    }

    private fun startTodosActivity() {
        Intent(this@MainActivity, TodosActivity::class.java).apply {
            startActivity(this)
        }
    }
}