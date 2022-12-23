package com.gaurav145.wallocity.activities

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.drawToBitmap
import com.bumptech.glide.Glide
import com.gaurav145.wallocity.databinding.ActivitySetWallpaperBinding
import com.gaurav145.wallocity.fragment.HomeFragment
import java.io.IOException

class SetWallpaper : AppCompatActivity() {
    private lateinit var binding: ActivitySetWallpaperBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        var wallpaperManager: WallpaperManager = WallpaperManager.getInstance(applicationContext)
        val intent = intent
        var srcUrl = intent.getStringExtra(HomeFragment.WALL_SRC)
        var srcId = intent.getStringExtra(HomeFragment.WALL_SRC_ID)
        Glide.with(applicationContext)
            .load(srcUrl)
            .into(binding.imgSetWallpaper)


        binding.btnSetWallpaper.setOnClickListener {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    wallpaperManager.setBitmap(
                        binding.imgSetWallpaper.drawToBitmap(),
                        null,
                        true,
                        WallpaperManager.FLAG_SYSTEM
                    )
                    Toast.makeText(
                        applicationContext,
                        "Wallpaper set successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: IOException) {
                Log.d("error", e.toString())
            }
        }
        binding.btnDownloadWallpaper.setOnClickListener {
            try {
                var downloadManager: DownloadManager =
                    getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                var uri: Uri = Uri.parse(srcUrl)
                var request: DownloadManager.Request = DownloadManager.Request(uri)
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI + DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle("Wallocity_$srcId")
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_PICTURES,
                        "Wallpaper_$srcId.jpg"
                    )
                downloadManager.enqueue(request)
                Toast.makeText(applicationContext, "Download Complete", Toast.LENGTH_LONG).show()

            } catch (e: IOException) {
                Log.d("error", e.toString())
            }
        }
    }
}