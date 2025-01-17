package com.dicoding.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.domain.Artwork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : ViewModel() {
    private val _art = MutableStateFlow<Artwork?>(null)
    val art = _art.asStateFlow()
}