package com.gaurav145.wallocity.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.gaurav145.wallocity.activities.MainActivity
import com.gaurav145.wallocity.activities.SetWallpaper
import com.gaurav145.wallocity.adapter.CategoriesWallpaperAdapter
import com.gaurav145.wallocity.databinding.FragmentWallpaperBinding
import com.gaurav145.wallocity.models.Photo
import com.gaurav145.wallocity.viewModel.HomeViewModel


class WallpaperFragment : Fragment() {

lateinit var binding:FragmentWallpaperBinding
lateinit var categoriesWallpaperAdapter: CategoriesWallpaperAdapter
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // binding.root.removeAllViews()
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentWallpaperBinding.inflate(inflater)
        binding.progressCircular1.visibility=View.VISIBLE
        prepareWallpaperRecycleView()
        observerWallpaper()
        onWallpaperClick()
        return binding.root
    }
    private fun prepareWallpaperRecycleView() {
        categoriesWallpaperAdapter= CategoriesWallpaperAdapter()
        binding.wallpaperRecycleViewFrag.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = categoriesWallpaperAdapter
        }
    }

    private fun observerWallpaper() {
        var text: String = requireArguments().getString(CategoriesFragment.CAT_NAME)!!
        viewModel.getWallpaper(text, 1)
        viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                val response = it.body()
                if (response != null) {
                    categoriesWallpaperAdapter.setWallpapers(
                        response.photos as ArrayList<Photo>,
                        requireContext()
                    )
                    binding.progressCircular1.visibility=View.GONE
                }
            }
        })

    }
    private fun onWallpaperClick() {
        categoriesWallpaperAdapter.onItemClick = {
            val intent = Intent(activity, SetWallpaper::class.java)
            intent.putExtra(HomeFragment.WALL_SRC, it.src.portrait)
            intent.putExtra(HomeFragment.WALL_SRC_ID, it.id.toString())
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.progressCircular1.visibility=View.VISIBLE
        categoriesWallpaperAdapter.setWallpapers(ArrayList(emptyList()), context?.applicationContext!!)
    }
    companion object {

    }
}