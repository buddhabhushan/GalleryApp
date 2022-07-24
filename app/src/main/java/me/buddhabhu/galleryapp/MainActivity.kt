package me.buddhabhu.galleryapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import me.buddhabhu.galleryapp.navigation.SetUpNavGraph
import me.buddhabhu.galleryapp.ui.SettingsAlertDialogue
import me.buddhabhu.galleryapp.ui.theme.GalleryAppTheme
import me.buddhabhu.galleryapp.utils.getImages
import me.buddhabhu.galleryapp.viewmodel.GalleryViewModel


class MainActivity : ComponentActivity() {

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

        if(requestCode == 101 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Granted", Toast.LENGTH_LONG).show()
            getImages(applicationContext, viewModel)
        } else {
            Toast.makeText(this, "Not Granted", Toast.LENGTH_LONG).show()
            setContent {
                SettingsAlertDialogue(
                    onAlertCancelled = ::onAlertCancelled,
                    onPositiveButtonClicked = ::onPositiveButtonClicked,
                )
            }
        }
    }

    private fun onAlertCancelled() {
        finish()
    }

    private fun onPositiveButtonClicked() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}