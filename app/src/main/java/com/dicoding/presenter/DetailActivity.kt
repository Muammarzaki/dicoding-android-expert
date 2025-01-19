package com.dicoding.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dicoding.presenter.viewmodel.DetailViewModel
import com.dicoding.ui.screen.DetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artId = intent.getStringExtra(EXTRA_ID)
        if (artId == null) finish()

        artId?.let { detailViewModel.getArtWork(it) }

        setContent {
            Surface {
                DetailScreen(
                    modifier = Modifier,
                    viewModel = detailViewModel,
                    onBackClick = { finish() },
                )
            }
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val IS_FAVORITE = "is_favorite"
    }
}