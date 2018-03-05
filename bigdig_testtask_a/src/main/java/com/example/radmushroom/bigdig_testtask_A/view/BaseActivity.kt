package com.example.radmushroom.bigdig_testtask_A.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity: AppCompatActivity() {

    protected abstract fun getContentView(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
    }

}