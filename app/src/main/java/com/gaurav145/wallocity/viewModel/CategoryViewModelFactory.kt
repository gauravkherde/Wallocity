package com.gaurav145.wallocity.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gaurav145.wallocity.retrofit.WallpaperRepository

class CategoryViewModelFactory (private  val repository: WallpaperRepository): ViewModelProvider.Factory {
    override  fun <T : ViewModel> create(modelClass: Class<T>): T{
        return  CategoryViewModel(repository) as T
    }
}