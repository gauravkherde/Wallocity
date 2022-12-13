package com.gaurav145.wallocity.retrofit

import retrofit2.http.Query

class WallpaperRepository {
    suspend fun getWallpaper(query: String)= RetrofitInstance.api.getWallpaper(query)
    suspend fun getAllWallpaper(page:Int)= RetrofitInstance.api.getAllWallpaper(page)
}