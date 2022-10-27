package com.example.didinoapp.view.fragments.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnticipateOvershootInterpolator
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.didinoapp.R
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.SearchItemBinding


class SearchAdapter(
    val context: Context,
    var IItemClickListener: (view: View, data: MovieAndSeriesModel) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var products = ArrayList<MovieAndSeriesModel>()
    fun setProducts(products: List<MovieAndSeriesModel>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataToLayout(data: MovieAndSeriesModel) {
            binding.searchModel = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: SearchItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.search_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.setDataToLayout(products[position])
        holder.itemView.setOnClickListener {
            IItemClickListener(holder.binding.root, products[position])
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}