package com.example.movieapp.feature_tv_show.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.feature_tv_show.domain.model.tvshow.TvShow
import com.example.movieapp.feature_tv_show.domain.use_case.TvShowUseCases
import com.example.movieapp.util.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tvShowUseCases: TvShowUseCases
): ViewModel() {

    private val _tvShowState = MutableStateFlow<PagingData<TvShow>>(value = PagingData.empty())
    val tvShowState: StateFlow<PagingData<TvShow>>
    get() = _tvShowState

    init {
        viewModelScope.launch {
            tvShowUseCases.getTvShows(pageSize = PAGE_SIZE)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect{
                    _tvShowState.value = it
                }
        }
    }

}