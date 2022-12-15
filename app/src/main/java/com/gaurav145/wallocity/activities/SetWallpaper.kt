package com.gaurav145.wallocity.activities

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.drawToBitmap
import com.bumptech.glide.Glide
import com.gaurav145.wallocity.R
import com.gaurav145.wallocity.databinding.ActivitySetWallpaperBinding
import com.gaurav145.wallocity.fragment.HomeFragment
import java.io.IOException

class SetWallpaper : AppCompatActivity() {
    private lateinit var binding: ActivitySetWallpaperBinding
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetWallpaperBinding.inflate(layoutInflater)
         setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        var wallpaperManager:WallpaperManager = WallpaperManager.getInstance(applicationContext)
        val intent = intent
        var srcUrl = intent.getStringExtra(HomeFragment.WALL_SRC)
        Glide.with(applicationContext)
            .load(srcUrl)
            .into(binding.imgSetWallpaper)


        binding.btnSetWallpaper.setOnClickListener {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    wallpaperManager.setBitmap(binding.imgSetWallpaper.drawToBitmap(),null,true,WallpaperManager.FLAG_SYSTEM)
                    Toast.makeText(applicationContext, "Wallpaper set successfully", Toast.LENGTH_LONG).show()
                }
            }catch (e: IOException)
            {
                Log.d("error",e.toString())
            }
        }
    }
}