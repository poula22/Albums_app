package com.bosta_task.album.presentation.profileScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bosta_task.album.presentation.profileScreen.AlbumUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AlbumListItem(album: AlbumUiModel,onAlbumClicked:(AlbumUiModel)->Unit ) {

    Column(
        Modifier
            .fillMaxWidth()
            .clickable {
                onAlbumClicked(album)
            },

    ) {
        Text(modifier = Modifier.padding(15.dp), text = album.title, style = MaterialTheme.typography.bodySmall)
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}