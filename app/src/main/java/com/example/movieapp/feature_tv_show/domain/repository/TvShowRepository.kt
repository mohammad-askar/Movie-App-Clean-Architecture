package com.example.movieapp.feature_tv_show.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.feature_tv_show.domain.model.tvshow.TvShow
import com.example.movieapp.feature_tv_show.domain.model.tvshowdetail.TvShowDetail
import com.example.movieapp.util.Recourse
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    /**
     *  Get the List of tvShow with pagination from a remote data source
     *  @param pageSize [Int] represent the amount of tvShow to load per page
     *  @return [Flow]<[PagingData]<[TvShow]>>
     */
    fun getTvShows(pageSize: Int): Flow<PagingData<TvShow>>

    /**
     *  Get the detail about a tvShow with cast
     *  @param id [Int] the id of the TvShow
     *  @return [Recourse]<[TvShowDetail]>
     */
    suspend fun getTvShowDetail(id: Long): Recourse<TvShowDetail?>
}