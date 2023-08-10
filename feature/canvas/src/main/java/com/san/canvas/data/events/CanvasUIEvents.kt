package com.san.canvas.data.events

import androidx.compose.ui.graphics.Color

sealed class CanvasUIEvents {

    data class UpdateUndoVisibility(val isVisible: Boolean) : CanvasUIEvents()
    data class UpdateRedoVisibility(val isVisible: Boolean) : CanvasUIEvents()
    data class UpdateColorBarVisibility(val isVisible: Boolean) : CanvasUIEvents()
    data class UpdateSizeVisibility(val isVisible: Boolean) : CanvasUIEvents()
    data class UpdateColor(val color: Color) : CanvasUIEvents()
    data class UpdateBgColor(val color: Color) : CanvasUIEvents()
    data class UpdateSize(val size: Float) : CanvasUIEvents()
    data class UpdateColorIsBG(val isVisible: Boolean) : CanvasUIEvents()
}
