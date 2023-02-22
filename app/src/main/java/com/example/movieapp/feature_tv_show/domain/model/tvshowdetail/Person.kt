package com.example.movieapp.feature_tv_show.domain.model.tvshowdetail

import com.example.movieapp.feature_tv_show.domain.model.tvshow.Image

data class Person(
    val id: Long,
    val image: Image?,
    val name: String,
    val updated: Long,
    val url: String
)
