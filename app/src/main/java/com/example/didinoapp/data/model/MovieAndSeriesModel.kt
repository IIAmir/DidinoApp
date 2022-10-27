package com.example.didinoapp.data.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieAndSeriesModel(
    val id: Int,
    val name_persian_SE: String,
    val name_foreign_SE: String,
    val vote_average: String,
    val about: String,
    val story: String,
    val preview_video: String,
    val actors: String,
    val director: String,
    val producer: String,
    val the_writer: String,
    val production_year: String,
    val main_posturl: String,
    val background_posturl: String,
    val grouping_id:String,
    val detail_gallery: String? = null,
    val movie_logo: String? = null
) : Parcelable {

    companion object {
        @JvmStatic
        @BindingAdapter("android:loadImageFromSite2")
        fun loadImageView(imageView: ImageView, link_url: String) {
            Picasso.get().load(link_url).fit().into(imageView)
        }
    }
}