package com.gaurav145.wallocity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gaurav145.wallocity.databinding.ItemLayoutBinding
import com.gaurav145.wallocity.models.Photo


class WallpaperAdapter: RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {

     var  wallpaperList = ArrayList<Photo>()
    lateinit var  contextx: Context
    var onItemClick : ((Photo) -> Unit?)? =null

    fun setWallpapers(wallpapers: ArrayList<Photo>,context: Context){
        this.wallpaperList=wallpapers
        contextx=context
        notifyDataSetChanged()
    }
    inner class  WallpaperViewHolder( val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        return WallpaperViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        Glide.with(holder.itemView).load(wallpaperList[position].src.portrait).into(holder.binding.imgWallpaper)

        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(wallpaperList[position])
        }
    }

    override fun getItemCount(): Int {
        return  wallpaperList.size
    }

}