package me.buddhabhu.galleryapp.utils

import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import me.buddhabhu.galleryapp.model.Picture
import me.buddhabhu.galleryapp.viewmodel.GalleryViewModel

@RequiresApi(Build.VERSION_CODES.Q)
fun getImages(
    context: Context,
    viewModel: GalleryViewModel,
) {

    val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    val projection: Array<String> = arrayOf(
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.BUCKET_ID,
        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Images.Media.DATE_ADDED,
    )
    val orderBy = MediaStore.Images.Media.DATE_ADDED

    val listPictures: MutableList<Picture> = mutableListOf()

    val cursor = context.contentResolver.query(uri, projection, null, null, "$orderBy DESC")

    while(cursor?.moveToNext() == true) {
        val picture = Picture(
            url = cursor.getString(0),
            bucketId = cursor.getString(1),
            bucketName = cursor.getString(2),
            dateAdded = cursor.getString(3),
        )

        listPictures.add(picture)
    }

    for(picture in listPictures) {
        println("DATA RECEIVED : $picture")
    }

    viewModel.picturesMap = listPictures.groupBy { it.bucketId!! }.toMutableMap()
}