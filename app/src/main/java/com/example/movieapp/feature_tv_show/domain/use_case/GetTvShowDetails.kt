package com.example.movieapp.feature_tv_show.domain.use_case

import com.example.movieapp.feature_tv_show.domain.model.tvshowdetail.TvShowDetail
import com.example.movieapp.feature_tv_show.domain.repository.TvShowRepository
import com.example.movieapp.util.Resource

class GetTvShowDetails(private val repository: TvShowRepository) {

    suspend operator fun invoke(id: Long): Resource<TvShowDetail?> {
        return repository.getTvShowDetail(id = id)
    }
}