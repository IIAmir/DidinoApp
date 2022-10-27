package com.example.didinoapp.view.fragments.didino.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.didinoapp.R
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.ItemSpecialBinding

class SpecialMovieAdapter(
    val context: Context,
    val data: List<MovieAndSeriesModel>,
    var IItemClickListener: (view: View, data: MovieAndSeriesModel) -> Unit
) :
    RecyclerView.Adapter<SpecialMovieAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSpecialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataToLayout(data: MovieAndSeriesModel) {
            binding.mySpecialModel = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecialMovieAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemSpecialBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_special, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecialMovieAdapter.ViewHolder, position: Int) {
        holder.setDataToLayout(data[position])
        holder.itemView.setOnClickListener {
            IItemClickListener(holder.binding.root, data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}