package com.dicoding.presenter.viewmodel

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dicoding.domain.ArtCard
import com.dicoding.domain.IArtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    artsUseCase: IArtsUseCase
) : ViewModel() {

    val arts = artsUseCase.getAllArts().cachedIn(viewModelScope)
    val favoriteArts = artsUseCase.getFavoriteArts().cachedIn(viewModelScope)

    val favoriteListState = LazyStaggeredGridState()
    val mainListState = LazyStaggeredGridState()

}