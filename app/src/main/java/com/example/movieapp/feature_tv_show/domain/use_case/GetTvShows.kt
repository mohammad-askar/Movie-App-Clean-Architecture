package com.example.movieapp.feature_tv_show.domain.use_case

import androidx.paging.PagingData
import com.example.movieapp.feature_tv_show.domain.model.tvshow.TvShow
import com.example.movieapp.feature_tv_show.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow

class GetTvShows(private val repository: TvShowRepository) {

    operator fun invoke(pageSize: Int): Flow<PagingData<TvShow>> {
        return repository.getTvShows(pageSize = pageSize)
    }
}