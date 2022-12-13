package com.gaurav145.wallocity.retrofit

import com.gaurav145.wallocity.models.WallpaperList
import com.gaurav145.wallocity.models.WallpaperResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WallpaperApi {

    @Headers("Authorization: 563492ad6f91700001000001242f68b7e501482abbf296fa0a64570a")
    @GET("search")
   suspend fun getWallpaper(@Query("query")query:String ="nature",
                           @Query("per_page")perPage:Int=80) : Response<WallpaperResponse>
    //fun getSearchDetail(@Query("page")page:Int,@Query("per_page")perPage:Int) : Call<SearchModel>

    @Headers("Authorization: 563492ad6f91700001000001242f68b7e501482abbf296fa0a64570a")
    @GET("curated")
    suspend fun getAllWallpaper(@Query("page")page:Int,
                                @Query("per_page")perPage:Int=80) : Response<WallpaperResponse>
}