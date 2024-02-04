package com.br.alura.galeria.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

fun Context.persistUriPermission(uri: Uri) {
    val contentResolver = contentResolver
    val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
    contentResolver.takePersistableUriPermission(uri, takeFlags)
}

suspend fun Context.getImagesFromTreeUri(
    treeUri: Uri,
    onLoadImages: (List<String>) -> Unit
) {
    val images = mutableListOf<String>()

    val treeDocumentId = DocumentsContract.getTreeDocumentId(treeUri)
    val childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(treeUri, treeDocumentId)

    withContext(IO) {
        contentResolver.query(
            childrenUri, arrayOf(
                DocumentsContract.Document.COLUMN_DISPLAY_NAME,
                DocumentsContract.Document.COLUMN_DOCUMENT_ID,
                DocumentsContract.Document.COLUMN_MIME_TYPE
            ),
            null,
            null,
            null
        )?.use { cursor ->
            val idIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DOCUMENT_ID)
            val mimeTypeIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_MIME_TYPE)

            while (cursor.moveToNext()) {
                val mimeType = cursor.getString(mimeTypeIndex)

                if (mimeType.startsWith("image/")) {
                    val documentId = cursor.getString(idIndex)
                    val documentUri =
                        DocumentsContract.buildDocumentUriUsingTree(treeUri, documentId)
                    images.add(documentUri.toString())
                }
            }
            withContext(Main) {
                onLoadImages(images)
            }
        }
    }
}

fun Context.getRootStorage(): Uri {
    return Uri.parse(getExternalFilesDir(null)?.path)
}