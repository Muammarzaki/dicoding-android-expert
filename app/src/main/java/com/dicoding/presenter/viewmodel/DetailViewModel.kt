package com.dicoding.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class DetailViewModel
@Inject constructor(
    private val useCase: IArtsUseCase
) : ViewModel() {

    private val _art = MutableStateFlow<ArtWork?>(null)
    val art = _art.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun getArtWork(artId: String) {
        useCase.getArtWork(artId).onEach {
            _art.emit(it)
        }.launchIn(viewModelScope)
    }

    fun toggleFavorite(artId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isFavorite.value.let {
                if (!it) {
                    useCase.addFavorite(artId).also {
                        Log.d("DetailViewModel", "toggleFavorite: True")
                    }
                } else {
                    useCase.deleteFavorite(artId).also {
                        Log.d("DetailViewModel", "toggleFavorite: False")
                    }
                }
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            art.collectLatest { artId ->
                useCase.isFavorite(artId = artId?.id.toString()).collectLatest {
                    _isFavorite.emit(it)
                }
            }
        }
    }
}