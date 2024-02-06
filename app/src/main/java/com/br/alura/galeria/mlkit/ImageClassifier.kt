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

        val options = ImageLabelerOptions.Builder()
            .setConfidenceThreshold(0.7f)
            .build()

        val labeler = ImageLabeling.getClient(options)

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