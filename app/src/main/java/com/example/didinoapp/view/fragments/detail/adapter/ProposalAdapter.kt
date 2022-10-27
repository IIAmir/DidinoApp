package com.example.didinoapp.view.fragments.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.didinoapp.R
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.ItemMovieDefaultBinding

class ProposalAdapter(
    val context: Context,
    var IItemClickListener: (view: View, data: MovieAndSeriesModel) -> Unit
) : RecyclerView.Adapter<ProposalAdapter.ViewHolder>() {

    var products = ArrayList<MovieAndSeriesModel>()

    fun setProducts(products: List<MovieAndSeriesModel>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMovieDefaultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataToLayout(data: MovieAndSeriesModel) {
            binding.myMovieDefaultModel = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProposalAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemMovieDefaultBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_movie_default, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProposalAdapter.ViewHolder, position: Int) {
        holder.setDataToLayout(products[position])
        startAnim(holder.binding.itemRelative)

        holder.itemView.setOnClickListener {

            IItemClickListener(holder.binding.root, products[position])
            val posi = products.removeAt(position)
            notifyItemRemoved(position)

            if (posi in products){
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,products.size - 1)
            }else{
                products.add(posi)
                notifyItemInserted(position)
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    private fun startAnim(view:View){
        val anim = AnimationUtils.loadAnimation(context,R.anim.alpha)
        view.startAnimation(anim)
    }
}