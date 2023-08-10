package com.san.canvas.data

import android.os.Parcelable
import com.san.canvas.drawBox.DrawController
import com.san.canvas.drawBox.components.defaultSelectedColor
import kotlinx.parcelize.Parcelize

data class CanvasScreenState(
    val undoVisibility: Boolean = false,
    val redoVisibility: Boolean = false,
    val colorBarVisibility: Boolean = false,
    val sizeBarVisibility: Boolean = false,
    val currentColor: ULong = defaultSelectedColor.value,
    val backgroundColor: ULong = defaultSelectedColor.value,
    val currentSize: Float = 10f,
    val colorIsBg: Boolean = false,
    val drawController: DrawController = DrawController(),
)