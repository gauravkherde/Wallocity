package com.gaurav145.wallocity.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.gaurav145.wallocity.R
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
    private var page: Int = 1
    var minimalClick: Boolean = false
    var abstractClick: Boolean = false
    var gameClick: Boolean = false
    var natureClick: Boolean = false
    var trendingClick: Boolean = false
    var sportClick: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //  callViewModel()
        viewModel = (activity as MainActivity).viewModel
        prepareWallpaperRecycleView()
        observerWallpaper()
        onSearchButton()
        onWallpaperClick()
        onNextPageClick()
        onPreviousPageClick()
        binding.tvLoadMore.text = "1"
        return binding.root

    }

    private fun onNextPageClick() {

        binding.rightArrow.setOnClickListener {
            page++
            if (minimalClick) viewModel.getWallpaper("minimal", page)
            else if (abstractClick) viewModel.getWallpaper("abstract", page)
            else if (gameClick) viewModel.getWallpaper("game", page)
            else if (natureClick) viewModel.getWallpaper("nature", page)
            else if (trendingClick) viewModel.getWallpaper("trending", page)
            else if (sportClick) viewModel.getWallpaper("sport", page)
            else viewModel.getAllWallpaper(page)

        }
    }

    private fun onPreviousPageClick() {

        binding.leftArrow.setOnClickListener {
            page--
            if (page >= 1) {

                if (minimalClick) viewModel.getWallpaper("minimal", page)
                else if (abstractClick) viewModel.getWallpaper("abstract", page)
                else if (gameClick) viewModel.getWallpaper("game", page)
                else if (natureClick) viewModel.getWallpaper("nature", page)
                else if (trendingClick) viewModel.getWallpaper("trending", page)
                else if (sportClick) viewModel.getWallpaper("sport", page)
                else viewModel.getAllWallpaper(page)
            } else Toast.makeText(context, "You are first page", Toast.LENGTH_SHORT).show()
        }

    }


    private fun onSearchButton() {
        binding.imgSearch.setOnClickListener {
            var searchText: String = binding.tvSearch.text.toString()
            viewModel.getWallpaper(searchText, 1)
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.catRow1Col1.setOnClickListener {
            page = 1
            if (!minimalClick) {
                minimalClick = true
                viewModel.getWallpaper("minimal", page)
                viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
                    if (it.isSuccessful) {
                        val response = it.body()
                        if (response != null) {
                            wallpaperAdapter.setWallpapers(
                                response.photos as ArrayList<Photo>,
                                requireContext()
                            )
                            binding.catRow1Col1.setCardBackgroundColor(resources.getColor(R.color.grey))
                        }
                    }
                })
            } else if (minimalClick) {
                minimalClick = false
                viewModel.getAllWallpaper(1)
                binding.catRow1Col1.setCardBackgroundColor(resources.getColor(R.color.white))

            }
        }

        binding.catRow1Col2.setOnClickListener {
            page = 1
            if (!abstractClick) {
                abstractClick = true
                viewModel.getWallpaper("abstract", page)
                viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
                    if (it.isSuccessful) {
                        val response = it.body()
                        if (response != null) {
                            wallpaperAdapter.setWallpapers(
                                response.photos as ArrayList<Photo>,
                                requireContext()
                            )
                            binding.catRow1Col2.setCardBackgroundColor(resources.getColor(R.color.grey))
                        }
                    }
                })
            } else if (abstractClick) {
                abstractClick = false
                viewModel.getAllWallpaper(1)
                binding.catRow1Col2.setCardBackgroundColor(resources.getColor(R.color.white))

            }
        }

        binding.catRow2Col1.setOnClickListener {
            page = 1
            if (!gameClick) {
                gameClick = true
                viewModel.getWallpaper("game", page)
                viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
                    if (it.isSuccessful) {
                        val response = it.body()
                        if (response != null) {
                            wallpaperAdapter.setWallpapers(
                                response.photos as ArrayList<Photo>,
                                requireContext()
                            )
                            binding.catRow2Col1.setCardBackgroundColor(resources.getColor(R.color.grey))
                        }
                    }
                })
            } else if (gameClick) {
                gameClick = false
                viewModel.getAllWallpaper(1)
                binding.catRow2Col1.setCardBackgroundColor(resources.getColor(R.color.white))

            }
        }

        binding.catRow2Col2.setOnClickListener {
            page = 1
            if (!natureClick) {
                natureClick = true
                viewModel.getWallpaper("nature", page)
                viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
                    if (it.isSuccessful) {
                        val response = it.body()
                        if (response != null) {
                            wallpaperAdapter.setWallpapers(
                                response.photos as ArrayList<Photo>,
                                requireContext()
                            )
                            binding.catRow2Col2.setCardBackgroundColor(resources.getColor(R.color.grey))
                        }
                    }
                })
            } else if (natureClick) {
                natureClick = false
                viewModel.getAllWallpaper(1)
                binding.catRow2Col2.setCardBackgroundColor(resources.getColor(R.color.white))

            }
        }

        binding.catRow3Col1.setOnClickListener {
            page = 1
            if (!trendingClick) {
                trendingClick = true
                viewModel.getWallpaper("trending", page)
                viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
                    if (it.isSuccessful) {
                        val response = it.body()
                        if (response != null) {
                            wallpaperAdapter.setWallpapers(
                                response.photos as ArrayList<Photo>,
                                requireContext()
                            )
                            binding.catRow3Col1.setCardBackgroundColor(resources.getColor(R.color.grey))
                        }
                    }
                })
            } else if (trendingClick) {
                trendingClick = false
                viewModel.getAllWallpaper(1)
                binding.catRow3Col1.setCardBackgroundColor(resources.getColor(R.color.white))

            }
        }

        binding.catRow3Col2.setOnClickListener {
            page = 1
            if (!sportClick) {
                sportClick = true
                viewModel.getWallpaper("sport", page)
                viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
                    if (it.isSuccessful) {
                        val response = it.body()
                        if (response != null) {
                            wallpaperAdapter.setWallpapers(
                                response.photos as ArrayList<Photo>,
                                requireContext()
                            )
                            binding.catRow3Col2.setCardBackgroundColor(resources.getColor(R.color.grey))
                        }
                    }
                })
            } else if (sportClick) {
                sportClick = false
                viewModel.getAllWallpaper(1)
                binding.catRow3Col2.setCardBackgroundColor(resources.getColor(R.color.white))

            }
        }
    }

    private fun prepareWallpaperRecycleView() {
        wallpaperAdapter = WallpaperAdapter()
        binding.wallpaperRecycleView.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

            adapter = wallpaperAdapter
        }
    }

    private fun observerWallpaper() {
        viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                val response = it.body()
                if (response != null) {
                    wallpaperAdapter.setWallpapers(
                        response.photos as ArrayList<Photo>,
                        requireContext()
                    )
                    binding.tvLoadMore.text = page.toString()
                }
            }
        })


    }

    private fun onWallpaperClick() {
        wallpaperAdapter.onItemClick = {
            val intent = Intent(activity, SetWallpaper::class.java)
            intent.putExtra(WALL_SRC, it.portrait)
            startActivity(intent)
        }
    }

    companion object {
        const val WALL_SRC = "WALL_SRC"
    }

}