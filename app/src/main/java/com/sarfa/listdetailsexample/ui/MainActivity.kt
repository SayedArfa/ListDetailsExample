package com.sarfa.listdetailsexample.ui

import android.os.Bundle
import com.sarfa.listdetailsexample.core.base.BaseActivity
import com.sarfa.listdetailsexample.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}