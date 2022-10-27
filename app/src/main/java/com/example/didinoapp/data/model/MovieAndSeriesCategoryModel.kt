package com.example.didinoapp.data.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.didinoapp.R
import com.squareup.picasso.Picasso
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieAndSeriesCategoryModel(
    val genre: String,
    val genre_id:String,
    val image_url: String?
) : Parcelable {
    companion object {
        @JvmStatic
        @BindingAdapter("android:loadImageFromSite")
        fun loadImageFromSite(imageView: ImageView, link_url: String) {
            Picasso.get().load(link_url).fit().into(imageView)
        }
    }
}