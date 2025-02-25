package com.dicoding.favorite.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dicoding.core.domain.ArtWork
import com.dicoding.core.domain.IArtsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("OPT_IN_USAGE")
class FavoriteViewModel @Inject constructor(
    private val artsUseCase: IArtsUseCase
) : ViewModel() {

    val favoriteArts = artsUseCase.getFavoriteArts().cachedIn(viewModelScope)

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
        _art
            .filterNotNull()
            .flatMapLatest { art ->
                artsUseCase.isFavorite(art.id)
            }
            .onEach { isFavorite ->
                _isFavorite.value = isFavorite
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}