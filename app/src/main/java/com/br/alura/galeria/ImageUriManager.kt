package com.br.alura.galeria

import android.content.Context
import android.net.Uri
import com.br.alura.galeria.data.ImageWithLabels
import com.br.alura.galeria.extensions.getImagesFromTreeUri
import javax.inject.Inject

class ImageUriManager @Inject constructor(
    private val context: Context
) {
    private var imageUris: MutableList<String> = mutableListOf()
    private var imageWithLabels: MutableList<ImageWithLabels> = mutableListOf()

    private fun setImageUris(newUris: List<String>) {
        imageUris.clear()
        imageUris.addAll(newUris)
    }

    fun getImageUris(): List<String> {
        return imageUris
    }

    fun setImageWithLabels(newImageWithLabels: List<ImageWithLabels>) {
        imageWithLabels.clear()
        imageWithLabels.addAll(newImageWithLabels)
    }

    fun getImageWithLabels(): List<ImageWithLabels> {
        return imageWithLabels
    }

    suspend fun loadImagesFromFolder(
        folderPath: String,
        onLoaded: () -> Unit = {}
    ) {
        context.getImagesFromTreeUri(Uri.parse(folderPath)) { images ->
            setImageUris(images)
            onLoaded()
        }
    }
}
