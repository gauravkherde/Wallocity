package com.gaurav145.wallocity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gaurav145.wallocity.R
import com.gaurav145.wallocity.retrofit.WallpaperRepository
import com.gaurav145.wallocity.viewModel.HomeViewModel
import com.gaurav145.wallocity.viewModel.WallpaperViewModelFactory

class MainActivity : AppCompatActivity() {
    val viewModel :HomeViewModel by lazy {
        val repository = WallpaperRepository()

        val wallpaperViewModelFactory = WallpaperViewModelFactory(repository)
        ViewModelProvider(this,wallpaperViewModelFactory)[HomeViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}