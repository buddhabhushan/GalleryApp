package me.buddhabhu.galleryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.buddhabhu.galleryapp.ui.HomeScreenUI
import me.buddhabhu.galleryapp.ui.ImageUI
import me.buddhabhu.galleryapp.ui.ImagesListUI
import me.buddhabhu.galleryapp.viewmodel.GalleryViewModel

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    viewModel: GalleryViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {

        composable(
            route = Screen.Home.route,
        ) {
            HomeScreenUI(
                navController = navController,
                viewModel = viewModel,
            )
        }

        composable(
            route = Screen.ImageList.route,
            arguments = listOf(
                navArgument(ARGUMENT_FOLDER_NAME) {
                    type = NavType.StringType
                }
            )
        ) {
            val picturesList = viewModel.picturesMap[it.arguments?.getString(ARGUMENT_FOLDER_NAME)] ?: emptyList()
            ImagesListUI(
                navController = navController,
                picturesList = picturesList,
            )
        }

        composable(
            route = Screen.ImageScreen.route,
            arguments = listOf(
                navArgument(ARGUMENT_IMG_PATH) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val arg = it.arguments?.getString(ARGUMENT_IMG_PATH)
            ImageUI(
                imgPath = it.arguments?.getString(ARGUMENT_IMG_PATH).toString(),
            )
        }
    }
}