package com.gaurav145.wallocity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.gaurav145.wallocity.R
import com.gaurav145.wallocity.activities.MainActivity
import com.gaurav145.wallocity.adapter.WallpaperAdapter
import com.gaurav145.wallocity.databinding.FragmentHomeBinding
import com.gaurav145.wallocity.databinding.FragmentWallpaperBinding
import com.gaurav145.wallocity.models.Photo
import com.gaurav145.wallocity.viewModel.HomeViewModel


class WallpaperFragment : Fragment() {

lateinit var binding:FragmentWallpaperBinding
lateinit var wallpaperAdapter: WallpaperAdapter
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentWallpaperBinding.inflate(inflater)
        prepareWallpaperRecycleView()
      //  onApiCall()
        observerWallpaper()
        return binding.root
    }
    private fun prepareWallpaperRecycleView() {
        wallpaperAdapter= WallpaperAdapter()
        binding.wallpaperRecycleViewFrag.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = wallpaperAdapter
        }
    }

/*    private fun onApiCall() {

            viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
                if (it.isSuccessful) {
                    val response = it.body()
                    if (response != null) {
                        wallpaperAdapter.setWallpapers(
                            response.photos as ArrayList<Photo>,
                            requireContext()
                        )
                    }
                }
            })

    }*/
    private fun observerWallpaper() {
        var text: String = requireArguments().getString(CategoriesFragment.CAT_NAME)!!
        viewModel.getWallpaper(text, 1)
        viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                val response = it.body()
                if (response != null) {
                    wallpaperAdapter.setWallpapers(
                        response.photos as ArrayList<Photo>,
                        requireContext()
                    )
                }
            }
        })

    }
    companion object {

    }
}