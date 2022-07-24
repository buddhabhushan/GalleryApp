package me.buddhabhu.galleryapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import me.buddhabhu.galleryapp.model.Picture
import me.buddhabhu.galleryapp.navigation.SetUpNavGraph
import me.buddhabhu.galleryapp.ui.HomeScreenUI
import me.buddhabhu.galleryapp.ui.theme.GalleryAppTheme
import me.buddhabhu.galleryapp.utils.getImages
import me.buddhabhu.galleryapp.viewmodel.GalleryViewModel

class MainActivity : ComponentActivity() {

    private var listPictures: List<Picture> = listOf()
    private val viewModel by lazy { ViewModelProvider(this).get(GalleryViewModel::class.java) }
    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryAppTheme {

                navController = rememberNavController()
                SetUpNavGraph(
                    viewModel = viewModel,
                    navController = navController,
                )
            }
        }

        if(ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED) {

            getImages(applicationContext, viewModel)
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
        }

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 101) {
            grantResults[0] = PackageManager.PERMISSION_GRANTED
            Toast.makeText(this, "Granted", Toast.LENGTH_LONG).show()
            getImages(applicationContext, viewModel)
        } else {
            Toast.makeText(this, "Not Granted", Toast.LENGTH_LONG).show()
        }
    }
}