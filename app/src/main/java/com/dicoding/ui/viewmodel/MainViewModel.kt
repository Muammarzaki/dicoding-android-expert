package com.dicoding.ui.viewmodel

import android.util.Log
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dicoding.domain.ArtWork
import com.dicoding.domain.IArtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val artsUseCase: IArtsUseCase
) : ViewModel() {

    private val _art = MutableStateFlow<ArtWork?>(null)
    val art = _art.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun getArtWork(artId: String) {
        artsUseCase.getArtWork(artId).onEach {
            _art.emit(it)
        }.launchIn(viewModelScope)
    }

    fun clearArt() {
        _art.value = null
    }

    val arts = artsUseCase.getAllArts().cachedIn(viewModelScope)

    val mainListState = LazyStaggeredGridState()


    fun toggleFavorite(artId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isFavorite.value.let {
                if (it) {
                    artsUseCase.deleteFavorite(artId)
                    Log.d("MainViewModel", "toggleFavorite: True")
                } else {
                    artsUseCase.addFavorite(artId)
                    Log.d("MainViewModel", "toggleFavorite: False")
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            _art.collectLatest { art ->
                art?.id?.let {
                    artsUseCase.isFavorite(it).collectLatest { isFavorite ->
                        _isFavorite.emit(isFavorite)
                    }
                }
            }
        }
    }

}