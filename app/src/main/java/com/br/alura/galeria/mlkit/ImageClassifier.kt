package com.br.alura.galeria.mlkit

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import javax.inject.Inject

class ImageClassifier @Inject constructor(private val context: Context) {


    fun classifyImage(
        imageUri: String,
        onSuccess: (List<String>) -> Unit,
        onFail: () -> Unit
    ) {

        val image = InputImage.fromFilePath(context, Uri.parse(imageUri))

        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        labeler.process(image)
            .addOnSuccessListener { labels ->
                labels.forEach {
                    val labelAndConfidence = "${it.text} - ${it.confidence}"
                    Log.d("ImageDetailScreen", labelAndConfidence)
                }
                onSuccess(labels.map { it.text })
            }
            .addOnFailureListener { onFail() }
    }

}