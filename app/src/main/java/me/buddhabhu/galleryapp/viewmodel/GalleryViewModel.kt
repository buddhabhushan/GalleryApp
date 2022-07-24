package me.buddhabhu.galleryapp.viewmodel

import androidx.lifecycle.ViewModel
import me.buddhabhu.galleryapp.model.Picture

class GalleryViewModel: ViewModel() {

    var picturesMap: MutableMap<String, List<Picture>> = mutableMapOf()
}