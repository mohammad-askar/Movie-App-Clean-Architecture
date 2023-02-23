package com.example.movieapp.feature_tv_show.presentation.show

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.feature_tv_show.domain.model.tvshowdetail.TvShowDetail
import com.example.movieapp.feature_tv_show.domain.use_case.TvShowUseCases
import com.example.movieapp.util.Recourse
import com.example.movieapp.util.TV_SHOW_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val useCases: TvShowUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _tvShowState = MutableStateFlow<TvShowDetail?>(value = null)
    val tvShowState: StateFlow<TvShowDetail?>
        get() = _tvShowState

    init {
        savedStateHandle.get<String>(key = TV_SHOW_ID_KEY)?.let {
            val id = it.toLong()
            viewModelScope.launch {
                _isLoading.value = true

                _tvShowState.value = when (val response = useCases.getTvShowDetails(id = id)) {
                    is Recourse.Success -> response.data
                    else -> null
                }
                _isLoading.value = false
            }
        }
    }

}