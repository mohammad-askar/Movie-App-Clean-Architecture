package com.example.movieapp.feature_tv_show.domain.model.tvshowdetail

import com.example.movieapp.feature_tv_show.domain.model.tvshow.Image
import com.example.movieapp.feature_tv_show.domain.model.tvshow.Rating

data class TvShowDetail(
    val id: Long,
    val url: String,
    val name: String,
    val genres: List<String>,
    val premiered: String,
    val officialSite: String?,
    val rating: Rating,
    val image: Image,
    val summary: String,
    val updated: Long,
    val actors: List<Person>
)
