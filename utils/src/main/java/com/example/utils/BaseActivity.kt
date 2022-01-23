package com.example.utils

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setToolbarTitle(title: String)
}