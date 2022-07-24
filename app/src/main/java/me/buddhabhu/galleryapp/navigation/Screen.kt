package me.buddhabhu.galleryapp.navigation

const val ARGUMENT_FOLDER_NAME = "folderName"
const val ARGUMENT_IMG_PATH = "imgPath"

sealed class Screen(val route: String) {
     object Home: Screen(route = "home_screen")
     object ImageList: Screen(route = "image_list_screen/{$ARGUMENT_FOLDER_NAME}") {
          fun passFolderName(folderId: String): String {
               return "image_list_screen/$folderId"
          }
     }
     object ImageScreen: Screen(route = "image_screen/{$ARGUMENT_IMG_PATH}") {
          fun passImagePath(imgPath: String): String {
               return "image_screen/$imgPath"
          }
     }
}
