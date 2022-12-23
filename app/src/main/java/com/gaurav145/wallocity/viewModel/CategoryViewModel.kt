package com.gaurav145.wallocity.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaurav145.wallocity.models.WallpaperResponse
import com.gaurav145.wallocity.retrofit.WallpaperRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CategoryViewModel (val repository: WallpaperRepository): ViewModel() {
    lateinit var wallpaperLiveData: MutableLiveData<Response<WallpaperResponse>>
    private var page :Int=1
    init {

        wallpaperLiveData = MutableLiveData()
    }

    fun getWallpaper(s: String,page: Int) {
        viewModelScope.launch {
            val response = repository.getWallpaper(s,page)
            wallpaperLiveData.postValue(response)
        }
    }

}

