package com.example.movieapp.feature_tv_show.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.feature_tv_show.data.remote.api.Api
import com.example.movieapp.feature_tv_show.data.repository.paging.TvShowRemotePagingSource
import com.example.movieapp.feature_tv_show.domain.model.tvshow.TvShow
import com.example.movieapp.feature_tv_show.domain.model.tvshowdetail.TvShowDetail
import com.example.movieapp.feature_tv_show.domain.repository.TvShowRepository
import com.example.movieapp.util.RepositoryHelper
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow

class TvShowRepositoryImpl(private val api: Api) : TvShowRepository, RepositoryHelper() {

    override fun getTvShows(pageSize: Int): Flow<PagingData<TvShow>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            pagingSourceFactory = { TvShowRemotePagingSource(api = api) }
        ).flow
    }

    override suspend fun getTvShowDetail(id: Long): Resource<TvShowDetail?> {
        return when (val response = invokeApi { api.getTvShowDetail(id = id) }) {
            is Resource.Loading<*> -> Resource.Loading()
            is Resource.Success<*> -> Resource.Success(data = response.data?.toTvShowDetail())
            is Resource.Error<*> -> Resource.Error(error = response.error)
            else -> {
                Resource.Error(null)
            }
        }
    }
}