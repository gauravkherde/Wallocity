package com.gaurav145.wallocity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.gaurav145.wallocity.R
import com.gaurav145.wallocity.retrofit.WallpaperRepository
import com.gaurav145.wallocity.viewModel.HomeViewModel
import com.gaurav145.wallocity.viewModel.WallpaperViewModelFactory
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var mAdView: AdView
    private lateinit var firebasefirestore: FirebaseFirestore
    val viewModel: HomeViewModel by lazy {
        val repository = WallpaperRepository()

        val wallpaperViewModelFactory = WallpaperViewModelFactory(repository)
        ViewModelProvider(this, wallpaperViewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNav)
        val navController = Navigation.findNavController(this, R.id.mainFragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)

        firebasefirestore = FirebaseFirestore.getInstance()
        firebasefirestore.collection(COL_ID).document(DOC_ID)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.d("tag1", "Listen failed.", e)
                    return@addSnapshotListener
                }
                if (snapshot != null) {

                    val adsState = snapshot.data?.get(ADS_STATE).toString()
                    val adUnitId = snapshot.data?.get(BANNER_ID).toString()
                    if (adsState == "true") {
                        mAdView = findViewById<AdView>(R.id.adView)
                        val adRequest = AdRequest.Builder().build()
                        mAdView.loadAd(adRequest)
                        // mAdView.adUnitId=adUnitId
                    }
                } else {
                    Log.d("tag2", "Current data: null")
                }
            }
    }

    companion object {
        const val DOC_ID = "3OIkraauAnlqhBVxSBH9"
        const val COL_ID = "Ads"
        const val BANNER_ID = "banner_id"
        const val ADS_STATE = "Ads_state"
    }
}