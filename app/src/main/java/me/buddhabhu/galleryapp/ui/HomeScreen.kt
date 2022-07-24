package me.buddhabhu.galleryapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import me.buddhabhu.galleryapp.model.Picture
import me.buddhabhu.galleryapp.navigation.Screen
import me.buddhabhu.galleryapp.viewmodel.GalleryViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(
    navController: NavController,
    viewModel: GalleryViewModel,
) {

    val albums = viewModel.picturesMap.keys.toList()

    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {

        itemsIndexed(items = albums) { _, it ->
            FolderUI(
                navController = navController,
                picture = viewModel.picturesMap[it]?.first()
            )
        }

    }
}

@Composable
fun FolderUI(
    navController: NavController,
    picture: Picture?,
) {
    if(picture != null) {
        Card(
            modifier = Modifier
                .size(120.dp)
                .clickable {
                    navController.navigate(Screen.ImageList.passFolderName(picture.bucketId.toString()))
                },
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.Gray

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier.size(100.dp),
                    model = picture.url,
                    contentDescription = picture.bucketName ?: "Photo"
                )

                Text(
                    text = picture.bucketName ?: "Photo",
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

