package com.san.canvas.utils

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import java.io.File
import kotlin.io.path.useLines


internal fun Activity.checkAndAskPermission(continueNext: () -> Unit) {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P && ContextCompat.checkSelfPermission(
            this, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100
        )
        return
    }
    continueNext()
}

internal fun activityChooser(uri: Uri?) = Intent.createChooser(Intent().apply {
    type = "image/*"
    action = Intent.ACTION_VIEW
    data = uri
    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
}, "Select App")

//writing files to storage via scope and normal manner acc. to Api level
internal fun Context.saveImageOnExternal(bitmap: Bitmap): Uri? {
    var uri: Uri? = null
    try {
        val fileName = System.nanoTime().toString() + ".png"
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/")
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            } else {
                val directory =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                val file = File(directory, fileName)
                put(MediaStore.MediaColumns.DATA, file.absolutePath)
            }
        }

        uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        uri?.let {
            contentResolver.openOutputStream(it).use { output ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                values.apply {
                    clear()
                    put(MediaStore.Audio.Media.IS_PENDING, 0)
                }
                contentResolver.update(uri, values, null, null)
            }
        }
        return uri
    } catch (e: java.lang.Exception) {
        if (uri != null) {
            // Don't leave an orphan entry in the MediaStore
            contentResolver.delete(uri, null, null)
        }
        throw e
    }
}


internal fun Context.saveImageOnTemp(bitmap: Bitmap): Uri? {
    return try {
        File.createTempFile("${System.nanoTime()}", ".png", cacheDir).apply {
            this.outputStream().use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
        }.toUri()

    } catch (e: Exception) {
        Log.e("Create_File", e.localizedMessage)
        null
    }
}