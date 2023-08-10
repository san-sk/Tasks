package com.san.canvas.presentation.screen


import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import com.san.canvas.drawBox.DrawBox
import com.san.canvas.drawBox.components.ColorPicker
import com.san.canvas.data.events.CanvasUIEvents


@Composable
fun CanvaScreen(viewModel: CanvasViewModel, save: (Bitmap) -> Unit) {
    val state by viewModel.state.collectAsState()

    var colorIsBg by remember { mutableStateOf(false) }


    Box {
        Column {
            DrawBox(
                drawController = state.drawController,
                backgroundColor = Color(state.backgroundColor),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f, fill = true),
                bitmapCallback = { imageBitmap, error ->
                    imageBitmap?.let {
                        save(it.asAndroidBitmap())
                    }
                }
            ) { undoCount, redoCount ->
                viewModel.updateUIEvent(CanvasUIEvents.UpdateSizeVisibility(false))
                viewModel.updateUIEvent(CanvasUIEvents.UpdateColorBarVisibility(false))
                viewModel.updateUIEvent(CanvasUIEvents.UpdateUndoVisibility(undoCount != 0))
                viewModel.updateUIEvent(CanvasUIEvents.UpdateRedoVisibility(redoCount != 0))
            }

            ControlsBar(
                drawController = state.drawController,
                onDownloadClick = {
                    state.drawController.saveBitmap()
                },
                onColorClick = {
                    viewModel.updateUIEvent(
                        CanvasUIEvents.UpdateColorBarVisibility(
                            when (state.colorBarVisibility) {
                                false -> true
                                colorIsBg -> true
                                else -> false
                            }
                        )
                    )
                    colorIsBg = false
                    viewModel.updateUIEvent(CanvasUIEvents.UpdateSizeVisibility(false))
                },
                onBgColorClick = {
                    viewModel.updateUIEvent(
                        CanvasUIEvents.UpdateColorBarVisibility(
                            when (state.colorBarVisibility) {
                                false -> true
                                !colorIsBg -> true
                                else -> false
                            }
                        )
                    )
                    colorIsBg = true
                    viewModel.updateUIEvent(CanvasUIEvents.UpdateSizeVisibility(false))
                },
                onSizeClick = {
                    viewModel.updateUIEvent(CanvasUIEvents.UpdateSizeVisibility(!state.sizeBarVisibility))
                    viewModel.updateUIEvent(CanvasUIEvents.UpdateColorBarVisibility(false))
                },
                undoVisibility = state.undoVisibility,
                redoVisibility = state.redoVisibility,
                colorValue = Color(state.currentColor),
                bgColorValue = Color(state.backgroundColor)
            )
            ColorPicker(isVisible = state.colorBarVisibility, showShades = true) {
                if (colorIsBg) {
                    viewModel.updateUIEvent(CanvasUIEvents.UpdateBgColor(it))
                    state.drawController.changeBgColor(it)
                } else {
                    viewModel.updateUIEvent(CanvasUIEvents.UpdateColor(it))
                    state.drawController.changeColor(it)
                }
            }
            CustomSeekbar(
                isVisible = state.sizeBarVisibility,
                progress = state.currentSize,
                progressColor = MaterialTheme.colorScheme.secondary,
                thumbColor = Color(state.currentColor)
            ) {
                viewModel.updateUIEvent(CanvasUIEvents.UpdateSize(it))
                state.drawController.changeStrokeWidth(it)
            }
        }

    }
}