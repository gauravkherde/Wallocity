package com.gaurav145.wallocity.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gaurav145.wallocity.activities.MainActivity
import com.gaurav145.wallocity.adapter.WallpaperAdapter
import com.gaurav145.wallocity.databinding.FragmentHomeBinding
import com.gaurav145.wallocity.models.Photo
import com.gaurav145.wallocity.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    lateinit var wallpaperAdapter: WallpaperAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentHomeBinding.inflate(inflater, container,false)
        viewModel =(activity as MainActivity).viewModel
        prepareWallpaperRecycleView()
        observerWallpaper()

       /* binding.wallpaperRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val view = recyclerView.getChildAt(recyclerView.childCount - 1) as View
                val difference = view.bottom - (recyclerView.height + recyclerView.scrollY)
                if (difference == 0) {
                    Log.d("ennddd","endddd")
                    viewModel.getAllWallpaper(newState)
                }
            }
        })*/
        return binding.root

    }

    private fun prepareWallpaperRecycleView() {
        wallpaperAdapter= WallpaperAdapter()
        binding.wallpaperRecycleView.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter =wallpaperAdapter
        }
    }
    private fun observerWallpaper() {
        viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful)
            {
                val response =it.body()
                if (response!=null)
                {
                    wallpaperAdapter.setWallpapers(response.photos as ArrayList<Photo>,requireContext())
                }
            }
        })
    }
  /*  private fun onWallpaperClick() {
        wallpaperAdapter.onItemClick={
            val intent= Intent(activity,SetWallpaper::class.java)
            intent.putExtra(WALL_SRC,it.portrait)
            startActivity(intent)
        }
    }
    private fun observerWallpaper() {
        viewModel.observeWallpaperLiveData().observe(viewLifecycleOwner, Observer
        { wallpapers->
            wallpaperAdapter.setWallpapers(wallpapers as ArrayList<WallpaperList>)
        })
    }*/
    companion object {
        const val WALL_SRC = "WALL_SRC"
    }

}