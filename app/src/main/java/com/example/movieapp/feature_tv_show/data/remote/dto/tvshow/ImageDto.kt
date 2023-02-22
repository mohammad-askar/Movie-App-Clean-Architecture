package com.example.movieapp.feature_tv_show.data.remote.dto.tvshow

import android.os.Parcelable
import com.example.movieapp.feature_tv_show.domain.model.tvshow.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageDto(
    val medium: String,
    val original: String
): Parcelable{

    fun toImage(): Image {
        return Image(
            medium,
            original
        )
    }

}