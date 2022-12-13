package com.gaurav145.wallocity.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class  RetrofitInstance {
    companion object {

          private val retrofit by lazy {

            Retrofit.Builder()
                .baseUrl("https://api.pexels.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: WallpaperApi by lazy {
            retrofit.create(WallpaperApi::class.java)
        }
    }
}