package me.buddhabhu.galleryapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageUI(
    imgPath: String,
) {

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.Gray

    ) {
        AsyncImage(
            model = imgPath,
            contentDescription = "Photo"
        )
    }


}