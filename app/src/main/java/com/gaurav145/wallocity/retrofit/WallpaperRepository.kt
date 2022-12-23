package com.gaurav145.wallocity.retrofit


class WallpaperRepository {
    suspend fun getWallpaper(query: String,page:Int)= RetrofitInstance.api.getWallpaper(query,page)
    suspend fun getAllWallpaper(page:Int)= RetrofitInstance.api.getAllWallpaper(page)
}