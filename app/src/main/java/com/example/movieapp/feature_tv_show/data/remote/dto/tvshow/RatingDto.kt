package com.example.movieapp.feature_tv_show.data.remote.dto.tvshow

import android.os.Parcelable
import com.example.movieapp.feature_tv_show.domain.model.tvshow.Rating
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingDto(
    val average: Double?
): Parcelable{

    fun toRating(): Rating {
        return Rating(average = average ?: 0.0)
    }
}