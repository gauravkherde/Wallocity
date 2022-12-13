package com.gaurav145.wallocity.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.gaurav145.wallocity.activities.MainActivity
import com.gaurav145.wallocity.activities.SetWallpaper
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
        onSearchButton()
        onWallpaperClick()
        return binding.root

    }


        /*        binding.wallpaperRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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


    private fun onSearchButton() {
        binding.imgSearch.setOnClickListener {
            var searchText:String= binding.tvSearch.text.toString()
            viewModel.getWallpaper(searchText)
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.catRow1Col1.setOnClickListener {
            viewModel.getWallpaper("minimal")
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

        binding.catRow1Col2.setOnClickListener {
            viewModel.getWallpaper("abstract")
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

        binding.catRow2Col1.setOnClickListener {
            viewModel.getWallpaper("game")
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

        binding.catRow2Col2.setOnClickListener {
            viewModel.getWallpaper("nature")
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

        binding.catRow3Col1.setOnClickListener {
            viewModel.getWallpaper("trending")
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

        binding.catRow3Col2.setOnClickListener {
            viewModel.getWallpaper("sport")
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
   private fun onWallpaperClick() {
        wallpaperAdapter.onItemClick={
            val intent= Intent(activity, SetWallpaper::class.java)
            intent.putExtra(WALL_SRC,it.portrait)
            startActivity(intent)
        }
    }
   /* private fun observerWallpaper() {
        viewModel.observeWallpaperLiveData().observe(viewLifecycleOwner, Observer
        { wallpapers->
            wallpaperAdapter.setWallpapers(wallpapers as ArrayList<WallpaperList>)
        })
    }*/
    companion object {
        const val WALL_SRC = "WALL_SRC"
    }

}