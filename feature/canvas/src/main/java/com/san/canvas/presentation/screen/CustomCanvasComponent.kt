package com.san.canvas.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.san.canvas.drawBox.DrawController


@Composable
fun ControlsBar(
    drawController: DrawController,
    onDownloadClick: () -> Unit,
    onColorClick: () -> Unit,
    onBgColorClick: () -> Unit,
    onSizeClick: () -> Unit,
    undoVisibility: Boolean,
    redoVisibility: Boolean,
    colorValue: Color,
    bgColorValue: Color,
) {
    Row(
        modifier = Modifier
            .padding(0.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ControlsBarItem(
            Icons.Default.Download,
            "download",
            if (undoVisibility) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary
        ) {
            if (undoVisibility) onDownloadClick()
        }
        ControlsBarItem(
            Icons.Default.Undo,
            "undo",
            if (undoVisibility) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary
        ) {
            if (undoVisibility) drawController.unDo()
        }
        ControlsBarItem(
            Icons.Default.Redo,
            "redo",
            if (redoVisibility) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary
        ) {
            if (redoVisibility) drawController.reDo()
        }
        ControlsBarItem(
            Icons.Default.Refresh,
            "reset",
            if (redoVisibility || undoVisibility) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary
        ) {
            drawController.reset()
        }
        ControlsBarItem(
            Icons.Default.ColorLens,
            "background color",
            bgColorValue,
        ) {
            onBgColorClick()
        }
        ControlsBarItem(Icons.Default.Colorize, "stroke color", colorValue) {
            onColorClick()
        }
        ControlsBarItem(
            Icons.Default.BorderColor,
            "stroke size",
            MaterialTheme.colorScheme.primary
        ) {
            onSizeClick()
        }
    }
}

@Composable
fun RowScope.ControlsBarItem(
    resId: ImageVector,
    desc: String,
    colorTint: Color,
    border: Boolean = false,
    onClick: () -> Unit
) {
    val modifier = Modifier.size(24.dp)
    IconButton(
        onClick = onClick, modifier = Modifier.weight(1f, true)
    ) {
        Icon(
            resId,
            contentDescription = desc,
            tint = colorTint,
            modifier = if (border) modifier.border(
                0.5.dp,
                colorTint,
                shape = CircleShape
            ) else modifier
        )
    }
}

@Composable
fun CustomSeekbar(
    isVisible: Boolean,
    max: Float = 100f,
    progress: Float = max,
    progressColor: Color,
    thumbColor: Color,
    onProgressChanged: (Float) -> Unit
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { with(density) { -40.dp.roundToPx() } } +
                expandVertically(expandFrom = Alignment.Top) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Column(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Stroke Width", modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp))

            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = progress,
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                    thumbColor = thumbColor,
                    activeTrackColor = progressColor
                ),
                onValueChange = { onProgressChanged(it) })
        }
    }
}