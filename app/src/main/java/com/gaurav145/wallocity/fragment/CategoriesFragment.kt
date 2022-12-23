package com.gaurav145.wallocity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gaurav145.wallocity.R
import com.gaurav145.wallocity.adapter.CategoriesWallpaperAdapter
import com.gaurav145.wallocity.databinding.FragmentCategoriesBinding


class CategoriesFragment : Fragment() {
    lateinit var binding: FragmentCategoriesBinding
    private val bundle = Bundle()
    lateinit var categoriesWallpaperAdapter: CategoriesWallpaperAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater)
        categoriesWallpaperAdapter=CategoriesWallpaperAdapter()
        onCardsClick()
        return binding.root
    }

    private fun onCardsClick() {
        binding.fourCard.setOnClickListener {
            bundle.putString(CAT_NAME, "4K")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)

        }
        binding.animalCard.setOnClickListener {
            bundle.putString(CAT_NAME, "Animal")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.ArtCard.setOnClickListener {
            bundle.putString(CAT_NAME, "Art")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.BuildingCard.setOnClickListener {
            bundle.putString(CAT_NAME, "Building")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.CarCard.setOnClickListener {
            bundle.putString(CAT_NAME, "Car")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.CityScapeCard.setOnClickListener {
            bundle.putString(CAT_NAME, "City Scape")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.LightCard.setOnClickListener {
            bundle.putString(CAT_NAME, "Light")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.LoveCard.setOnClickListener {
            bundle.putString(CAT_NAME, "Love")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.MotorCycleCard.setOnClickListener {
            bundle.putString(CAT_NAME, "bike")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.PeopleCard.setOnClickListener {
            bundle.putString(CAT_NAME, "People")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.SpaceCard.setOnClickListener {
            bundle.putString(CAT_NAME, "Space")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.TechnologyCard.setOnClickListener {
            bundle.putString(CAT_NAME, "Technology")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }
        binding.WordCard.setOnClickListener {
            bundle.putString(CAT_NAME, "Word")
            findNavController().navigate(R.id.action_categoriesFragment_to_wallpaperFragment,bundle)
        }

    }
    override fun onResume() {
        super.onResume()
        categoriesWallpaperAdapter.clear()
    }
    companion object {
        const val CAT_NAME = "CAT_NAME"
    }
}