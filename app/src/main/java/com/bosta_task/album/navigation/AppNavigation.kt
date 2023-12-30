package com.bosta_task.album.navigation
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bosta_task.album.presentation.photoViewerScreen.ImageViewerScreen
import com.bosta_task.album.presentation.albumDetailsScreen.AlbumDetailsScreen
import com.bosta_task.album.presentation.albumDetailsScreen.AlbumDetailsViewModel
import com.bosta_task.album.presentation.profileScreen.AlbumUiModel
import com.bosta_task.album.presentation.profileScreen.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.PROFILE,
        exitTransition = { fadeOut(animationSpec = tween(100)) },
        enterTransition = { fadeIn(animationSpec = tween(300)) }
    ) {
        composable(route = Routes.PROFILE) {
            ProfileScreen(onAlbumClicked = { album ->
                navController.navigate("${Routes.ALBUM_DETAILS}/album?id=${album.id}&title=${album.title}")
            })
        }


        composable(
            route = "${Routes.ALBUM_DETAILS}/album?id={albumId}&title={albumTitle}",
            arguments = listOf(
                navArgument("albumId") {
                    nullable = false
                    type = NavType.IntType
                },
                navArgument("albumTitle") {
                    nullable = false
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val albumDetailsViewModel:AlbumDetailsViewModel= hiltViewModel()
            val albumUiState by albumDetailsViewModel.albumUiState.collectAsStateWithLifecycle()
            val albumId=backStackEntry.arguments?.getInt("albumId")!!
            val title=backStackEntry.arguments?.getString("albumTitle")!!

            LaunchedEffect(key1 = albumId) {
                albumDetailsViewModel.fetchPhotos(albumId)
            }

            AlbumDetailsScreen(
                album = AlbumUiModel(
                    id = albumId,
                    title =title ,
                    ),
                albumDetailsViewModel=albumDetailsViewModel,
                albumUiState=albumUiState
            ) {
                navController.navigate("${Routes.PHOTO_VIEWER}/photo?url=${it.url}&title=${it.title}")
            }
        }
        composable(
            route="${Routes.PHOTO_VIEWER}/photo?url={photoUrl}&title={photoTitle}",
            listOf(
                navArgument("photoUrl"){
                    nullable=false
                    type= NavType.StringType
                },
                navArgument("photoTitle"){
                    nullable=false
                    type= NavType.StringType
                }
            )
        ){navBackStack->
            ImageViewerScreen(
                photoUrl = navBackStack.arguments?.getString("photoUrl")!!,
                title =navBackStack.arguments?.getString("photoTitle")!!
            )
        }
    }
}