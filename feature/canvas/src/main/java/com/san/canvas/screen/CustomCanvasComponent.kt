package com.san.canvas.screen

import android.widget.SeekBar
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
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
    sizeValue: Int
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
    max: Int = 200,
    progress: Int = max,
    progressColor: Int,
    thumbColor: Int,
    onProgressChanged: (Int) -> Unit
) {
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Stroke Width", modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp))
            AndroidView(
                { SeekBar(context) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                it.progressDrawable.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        progressColor,
                        BlendModeCompat.SRC_ATOP
                    )
                it.thumb.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        thumbColor,
                        BlendModeCompat.SRC_ATOP
                    )
                it.max = max
                it.progress = progress
                it.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {}
                    override fun onStopTrackingTouch(p0: SeekBar?) {
                        onProgressChanged(p0?.progress ?: it.progress)
                    }
                })
            }
        }
    }
}