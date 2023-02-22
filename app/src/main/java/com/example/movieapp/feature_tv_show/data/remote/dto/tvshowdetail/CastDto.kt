package com.example.movieapp.feature_tv_show.data.remote.dto.tvshowdetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastDto(
    val person: PersonDto,
): Parcelable