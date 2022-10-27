package com.example.didinoapp.view.fragments.didino.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.didinoapp.R
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.ItemGalleryBinding

class GallerySliderAdapter(
    val context: Context,
    val data: List<MovieAndSeriesModel>,
    var IItemClickListener: (view: View, data: MovieAndSeriesModel) -> Unit,
    var IItemClickListenerShowMovie: (view: View, data: String) -> Unit
) :
    RecyclerView.Adapter<GallerySliderAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataToLayout(data: MovieAndSeriesModel) {
            binding.myGalleryModel = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GallerySliderAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemGalleryBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_gallery, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GallerySliderAdapter.ViewHolder, position: Int) {
        holder.setDataToLayout(data[position])

        holder.binding.btnShowDetail.setOnClickListener {
            IItemClickListener(holder.binding.btnShowDetail, data[position])
        }

        holder.binding.btnShowMovie.setOnClickListener {
            IItemClickListenerShowMovie(holder.binding.btnShowMovie, data[position].preview_video)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
//    PagerAdapter() {
//    var inflater: LayoutInflater = LayoutInflater.from(context)
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val binding = DataBindingUtil.inflate<ItemGalleryBinding>(
//            inflater,
//            R.layout.item_gallery, container, false
//        )
//        binding.setVariable(BR.myGalleryModel, data[position])
//        container.addView(binding.root)
//
//        return binding.root
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
//
//    override fun getCount(): Int {
//        return data.size
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
