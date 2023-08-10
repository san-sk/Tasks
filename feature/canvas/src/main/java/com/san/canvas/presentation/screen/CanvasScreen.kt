package com.san.canvas.presentation.screen

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import com.san.canvas.drawBox.DrawBox
import com.san.canvas.drawBox.components.ColorPicker
import com.san.canvas.drawBox.components.defaultSelectedColor
import com.san.canvas.drawBox.rememberDrawController


@Composable
fun CanvasScreen(save: (Bitmap) -> Unit) {

    var undoVisibility by remember { mutableStateOf(false) }
    var redoVisibility by remember { mutableStateOf(false) }
    var colorBarVisibility by remember { mutableStateOf(false) }
    var sizeBarVisibility by remember { mutableStateOf(false) }
    var currentColor by remember { mutableStateOf(defaultSelectedColor) }
    val bg = MaterialTheme.colorScheme.background
    var currentBgColor by remember { mutableStateOf(bg) }
    var currentSize by remember { mutableStateOf(10f) }
    var colorIsBg by remember { mutableStateOf(false) }
    val drawController = rememberDrawController()

    Box {
        Column {
            DrawBox(
                drawController = drawController,
                backgroundColor = currentBgColor,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f, fill = true),
                bitmapCallback = { imageBitmap, error ->
                    imageBitmap?.let {
                        save(it.asAndroidBitmap())
                    }
                }
            ) { undoCount, redoCount ->
                sizeBarVisibility = false
                colorBarVisibility = false
                undoVisibility = undoCount != 0
                redoVisibility = redoCount != 0
            }

            ControlsBar(
                drawController = drawController,
                onDownloadClick = {
                    drawController.saveBitmap()
                },
                onColorClick = {
                    colorBarVisibility = when (colorBarVisibility) {
                        false -> true
                        colorIsBg -> true
                        else -> false
                    }
                    colorIsBg = false
                    sizeBarVisibility = false
                },
                onBgColorClick = {
                    colorBarVisibility = when (colorBarVisibility) {
                        false -> true
                        !colorIsBg -> true
                        else -> false
                    }
                    colorIsBg = true
                    sizeBarVisibility = false
                },
                onSizeClick = {
                    sizeBarVisibility = !sizeBarVisibility
                    colorBarVisibility = false
                },
                undoVisibility = undoVisibility,
                redoVisibility = redoVisibility,
                colorValue = currentColor,
                bgColorValue = currentBgColor
            )
            ColorPicker(isVisible = colorBarVisibility, showShades = true) {
                if (colorIsBg) {
                    currentBgColor = it
                    drawController.changeBgColor(it)
                } else {
                    currentColor = it
                    drawController.changeColor(it)
                }
            }
            CustomSeekbar(
                isVisible = sizeBarVisibility,
                progress = currentSize,
                progressColor = MaterialTheme.colorScheme.secondary,
                thumbColor = currentColor
            ) {
                currentSize = it
                drawController.changeStrokeWidth(it)
            }
        }

    }
}