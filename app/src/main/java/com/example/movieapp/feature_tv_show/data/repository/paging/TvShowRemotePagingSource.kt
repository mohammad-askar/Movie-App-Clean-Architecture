package com.example.movieapp.feature_tv_show.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.feature_tv_show.data.remote.api.Api
import com.example.movieapp.feature_tv_show.domain.model.tvshow.TvShow
import java.io.IOException
import kotlin.math.exp

class TvShowRemotePagingSource(private val api: Api): PagingSource<Int, TvShow>() {

    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {

        val currentPage = params.key ?: 1
        val response = api.getTvShows(page = currentPage)
        val endOfPaginationReached = response.isEmpty()

        return try {
            LoadResult.Page(
                data = response.map { it.toTvShow() },
                // i used the minus method instead of  currentPage -1
                prevKey = if (currentPage == 1) null else currentPage.minus(1),
                nextKey = if (endOfPaginationReached) null else currentPage.plus(1)
            )
        }catch (exp: java.lang.Exception){
            LoadResult.Error(exp)
        }finally {
            LoadResult.Error<Int, TvShow>(throwable = IOException("Something went wrong"))
        }
    }
}