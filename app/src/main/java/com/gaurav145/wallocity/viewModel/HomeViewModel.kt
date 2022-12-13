package com.gaurav145.wallocity.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaurav145.wallocity.models.WallpaperList
import com.gaurav145.wallocity.models.WallpaperResponse
import com.gaurav145.wallocity.retrofit.RetrofitInstance
import com.gaurav145.wallocity.retrofit.WallpaperRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(val repository: WallpaperRepository): ViewModel() {
    lateinit var wallpaperLiveData: MutableLiveData<Response<WallpaperResponse>>
    private var page :Int=1
    init {

        wallpaperLiveData = MutableLiveData()
        getAllWallpaper(page)
       // getWallpaper("nature")
    }

    fun getWallpaper(s: String) {
        viewModelScope.launch {
            val response = repository.getWallpaper(s)
            wallpaperLiveData.postValue(response)
        }
    }
    fun getAllWallpaper(page:Int ) {
        this.page =page+1
        viewModelScope.launch {
            val response = repository.getAllWallpaper(page)
            wallpaperLiveData.postValue(response)
        }
    }
}


















/*
    fun getWallpaper() {

        RetrofitInstance.api.getImageDetail(1).enqueue(object :Callback<WallpaperResponse>{
            override fun onResponse(call: Call<WallpaperResponse>, response: Response<WallpaperResponse>) {
               response.body()?.let{
                    wallpaperLiveData.postValue(it)
                }
            }

            override fun onFailure(call: Call<WallpaperResponse>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })

    }
    fun observeWallpaperLiveData(): LiveData<WallpaperResponse> {
        return wallpaperLiveData
    }

}*/
