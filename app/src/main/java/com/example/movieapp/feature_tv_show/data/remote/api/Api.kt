package com.example.movieapp.feature_tv_show.data.remote.api

import com.example.movieapp.feature_tv_show.data.remote.dto.tvshow.TvShowDto
import com.example.movieapp.feature_tv_show.data.remote.dto.tvshowdetail.TvShowDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    /**
     * Get the list of tv shows with pagination
     * @param page [Int] page number to be fetched
     * @return [List]<[TvShawDto]>
     */

    @GET("shows")
    suspend fun getTvShows(
        @Query("page") page: Int
    ): List<TvShowDto>

    /**
     * Get the tv show with cast
     * @param id [Int] the id of the tv show
     * @return [TvShowDetailDto]
     */

    @GET("shows/{id}?embed=cast")
    suspend fun getTvShowDetail(
        @Path("id") id: Long
    ): TvShowDetailDto?
}