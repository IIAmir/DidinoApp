package com.example.didinoapp.view.fragments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SendMovieAndSeriesModel(
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
    val background_posturl: String
) : Parcelable {
}