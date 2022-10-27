package com.example.didinoapp.view.fragments.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.didinoapp.R
import com.example.didinoapp.data.model.MovieAndSeriesCategoryModel
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.CategoryItemBinding

class CategoryRecyclerViewAdapter(
    val context: Context,
    val data: List<MovieAndSeriesCategoryModel>,
    var IItemClickListener: (view: View, data: MovieAndSeriesCategoryModel) -> Unit
) :
    RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataToLayout(data: MovieAndSeriesCategoryModel) {
            binding.categoryModel = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryRecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: CategoryItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.category_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.setDataToLayout(data[position])
        holder.itemView.setOnClickListener {
            IItemClickListener(holder.binding.root, data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}