package com.example.didinoapp.view.fragments.didino.adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.didinoapp.R
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.databinding.ItemMovieDefaultBinding
import java.util.*
import kotlin.collections.ArrayList

class DefaultMovieAdapter(
    val context: Context,
    val data: List<MovieAndSeriesModel>,
    var IItemClickListener: (view: View, data: MovieAndSeriesModel) -> Unit
) :
    RecyclerView.Adapter<DefaultMovieAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemMovieDefaultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setDataToLayout(data: MovieAndSeriesModel) {
            binding.myMovieDefaultModel = data
        }

        fun animateItemView(itemView: View) {
            //hide the itemView
            itemView.alpha = 0f

            //moving the itemView down 400f
            ObjectAnimator.ofFloat(itemView, "translationY", 0f, 400f)
                .apply { duration = 1L }.start()

            //show
            //itemView.alpha = 1f

            //moving the itemView up 400f
            val translateUp = ObjectAnimator.ofFloat(itemView, "translationY", 400f, 0f)
                .apply {
                    duration = 1000L
                    interpolator = AnticipateOvershootInterpolator(2f)
                }

            //animating alpha
            val fade = ValueAnimator.ofFloat(0f, 1f)
                .apply {
                    addUpdateListener {
                        itemView.alpha = this.animatedValue as Float
                    }
                    duration = 400L
                }

            //applying
            AnimatorSet().apply { playTogether(translateUp, fade) }.start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultMovieAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemMovieDefaultBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_movie_default, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DefaultMovieAdapter.ViewHolder, position: Int) {
        holder.setDataToLayout(data[position])
//        setAnimItem(holder.binding.itemRelative, position, data.size)
        holder.itemView.setOnClickListener {
            IItemClickListener(holder.binding.root, data[position])
        }
    }

    private fun setAnimItem(itemView: View, itemPosition: Int, itemSize: Int) {
        if (itemSize <= 7) {
            val h = 200f
            itemView.translationY = h
            itemView.alpha = 0.5f
            itemView.animate().translationY(0f).alpha(1f)
                .setInterpolator(AnticipateOvershootInterpolator(2f))
                .setDuration(800L)
        } else {

        }
    }

    override fun getItemCount():Int = data.size
}