package com.bosta_task.album.presentation.albumDetailsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bosta_task.album.R
import com.bosta_task.album.presentation.albumDetailsScreen.component.SearchBar
import com.bosta_task.album.presentation.profileScreen.AlbumUiModel
import com.bosta_task.domain.customExceptions.EmptyListException
import com.bosta_task.domain.model.PhotoDomainModel
import kotlinx.coroutines.flow.flow

@Composable
fun AlbumDetailsScreen(
    album: AlbumUiModel,
    albumDetailsViewModel: AlbumDetailsViewModel = hiltViewModel(),
    albumUiState: AlbumUiState?,
    onPhotoClicked: (PhotoDomainModel) -> Unit
) {


    when (albumUiState) {
        is AlbumUiState.Success -> {
            val state = albumUiState.photosList
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    text = album.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineLarge
                )
                SearchBar(
                    onValueChanged = {
                        albumDetailsViewModel.searchInPhotos(it)
                    })
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(1f),
                    columns = GridCells.Fixed(3)
                ) {
                    items(
                        count = state.size,
                        key = { index ->
                            state[index].id
                        },
                        itemContent = { index ->
                            val photo = state[index]
                            AsyncImage(modifier = Modifier
                                .fillMaxWidth(0.33f)
                                .clickable {
                                    onPhotoClicked(photo)

                                },
                                model = photo.url,
                                contentDescription = photo.title,
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    )
                }
            }
        }

        is AlbumUiState.Error -> {
            if (albumUiState.throwable is EmptyListException) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.there_is_no_albums_to_show),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }

        is AlbumUiState.Loading -> {
            Box(modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        else -> {}
    }
}