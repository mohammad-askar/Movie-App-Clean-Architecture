package com.example.movieapp.feature_tv_show.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.feature_tv_show.data.remote.api.Api
import com.example.movieapp.feature_tv_show.data.repository.paging.TvShowRemotePagingSource
import com.example.movieapp.feature_tv_show.domain.model.tvshow.TvShow
import com.example.movieapp.feature_tv_show.domain.model.tvshowdetail.TvShowDetail
import com.example.movieapp.feature_tv_show.domain.repository.TvShowRepository
import com.example.movieapp.util.Recourse
import com.example.movieapp.util.RepositoryHelper
import kotlinx.coroutines.flow.Flow

class TvShowRepositoryImp(private val api: Api) : TvShowRepository, RepositoryHelper() {

    override fun getTvShows(pageSize: Int): Flow<PagingData<TvShow>> {

        return Pager(
            config = PagingConfig(pageSize),
            pagingSourceFactory = { TvShowRemotePagingSource(api) }
        ).flow
    }

    override suspend fun getTvShowDetail(id: Long): Recourse<TvShowDetail?> {
        return when (val response = invokeApi { api.getTvShowDetail(id = id) }) {
            is Recourse.Loading -> Recourse.Loading(data = null)
            is Recourse.Success -> Recourse.Success(data = response.data?.toTvShowDetail())
            is Recourse.Error -> Recourse.Error(data = null, error = response.error)
        }
    }
}