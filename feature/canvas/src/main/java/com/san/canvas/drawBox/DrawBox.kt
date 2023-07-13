package com.san.canvas.drawBox

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun DrawBox(
    drawController: DrawController,
    modifier: Modifier = Modifier.fillMaxSize(),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    bitmapCallback: (ImageBitmap?, Throwable?) -> Unit,
    trackHistory: (undoCount: Int, redoCount: Int) -> Unit = { _, _ -> }
) = AndroidView(
    factory = {
        ComposeView(it).apply {
            setContent {
                LaunchedEffect(drawController) {
                    drawController.changeBgColor(backgroundColor)
                    drawController.trackBitmaps(this@apply, this, bitmapCallback)
                    drawController.trackHistory(this, trackHistory)
                }
                Canvas(modifier = modifier
                    .background(drawController.bgColor)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { offset ->
                                drawController.insertNewPath(offset)
                                drawController.updateLatestPath(offset)
                                drawController.pathList
                            }
                        )
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                drawController.insertNewPath(offset)
                            }
                        ) { change, _ ->
                            val newPoint = change.position
                            drawController.updateLatestPath(newPoint)
                        }

                    }) {
                    drawController.pathList.forEach { pw ->
                        drawPath(
                            createPath(pw.points),
                            color = pw.strokeColor,
                            alpha = pw.alpha,
                            style = Stroke(
                                width = pw.strokeWidth,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )
                    }
                }
            }
        }
    },
    modifier = modifier
)





