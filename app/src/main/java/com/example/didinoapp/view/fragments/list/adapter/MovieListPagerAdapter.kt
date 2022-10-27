package com.example.didinoapp.view.fragments.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.didinoapp.R
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.ItemMovieDefaultBinding

class MovieListPagerAdapter(
    private val data: List<MovieAndSeriesModel>,
    var IItemClickListener: (view: View, data: MovieAndSeriesModel) -> Unit
) : PagingDataAdapter<MovieAndSeriesModel, MovieListPagerAdapter.MyViewHolder>(
    MovieAndSeriesComparator
) {

    object MovieAndSeriesComparator : DiffUtil.ItemCallback<MovieAndSeriesModel>() {
        override fun areItemsTheSame(
            oldItem: MovieAndSeriesModel,
            newItem: MovieAndSeriesModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieAndSeriesModel,
            newItem: MovieAndSeriesModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class MyViewHolder(val binding: ItemMovieDefaultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataToLayout(data: MovieAndSeriesModel) {
            binding.myMovieDefaultModel = data
        }
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.setDataToLayout(it) }

        holder.itemView.setOnClickListener {
            IItemClickListener(holder.binding.root, data[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieDefaultBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_movie_default, parent, false)

        return MyViewHolder(binding)
    }
}