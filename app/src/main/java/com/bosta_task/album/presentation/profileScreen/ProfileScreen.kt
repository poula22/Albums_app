package com.bosta_task.album.presentation.profileScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bosta_task.album.R
import com.bosta_task.album.presentation.profileScreen.component.AlbumListItem
import com.bosta_task.domain.customExceptions.EmptyListException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onAlbumClicked: (AlbumUiModel) -> Unit
) {
    val profileUiState by profileViewModel.profileUiState.collectAsStateWithLifecycle()
    var enabled by remember {
        mutableStateOf(true)
    }
    val scope = rememberCoroutineScope()

    when (profileUiState) {
        is ProfileUiState.Success -> {
            val state = (profileUiState as ProfileUiState.Success)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    text = stringResource(R.string.profile),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                    text = state.userProfile.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = state.userProfile.address,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                    text = stringResource(R.string.my_albums),
                    style = MaterialTheme.typography.bodyLarge
                )
                Divider(color = Color.LightGray, thickness = 1.dp)
                if (enabled) {
                    LazyColumn {
                        items(state.userAlbums) { item ->
                            AlbumListItem(album = item) {
                                onAlbumClicked(it)
                                enabled = false
                                scope.launch {
                                    delay(500)
                                    enabled = true
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                        }
                    }
                }
            }
        }

        is ProfileUiState.Loading -> {
            Box(modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is ProfileUiState.Error -> {
            if ((profileUiState as ProfileUiState.Error).throwable is EmptyListException) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.there_is_no_albums_to_show),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }

        else -> {
            //value is null
        }
    }
}