package com.san.canvas.drawBox.colorComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun ColorPicker(
    isVisible: Boolean,
    rowElementsCount: Int = defaultRowElementsCount,
    showShades: Boolean = false,
    colorIntensity: Int = defaultColorIntensity,
    unSelectedSize: Dp = defaultUnSelectedSize,
    selectedSize: Dp = defaultSelectedSize,
    colors: List<List<Color>> = colorArray,
    defaultColor: Color = defaultSelectedColor,
    clickedColor: (Color) -> Unit
) {
    val colorIntensity = if (colorIntensity in 10..-1) defaultColorIntensity else colorIntensity
    val density = LocalDensity.current

    var defaultColor by remember {
        mutableStateOf(defaultColor)
    }
    var defaultRow by remember {
        mutableStateOf(colors[0])
    }
    var subColorsRowVisibility by remember {
        mutableStateOf(true)
    }

    ChangeVisibility(isVisible, density) {
        val parentList = colors.chunked(rowElementsCount)
        Column(
            modifier = Modifier
                .padding(16.dp, 0.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {

            if (showShades) {
                ChangeVisibility(
                    subColorsRowVisibility,
                    density
                ) {
                    if (!defaultRow.contains(defaultColor)) {
                        defaultRow = colors.first { it.contains(defaultColor) }
                    }
                    val parentList = defaultRow.chunked(rowElementsCount)

                    Column {

                        parentList.forEachIndexed { _, colorRow ->
                            SubColorRow(
                                rowElementsCount = rowElementsCount,
                                colorRow = colorRow,
                                defaultColor = defaultColor,
                                unSelectedSize = unSelectedSize,
                                selectedSize = selectedSize
                            ) {
                                defaultColor = it
                                clickedColor(it)
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                        )
                    }

                }
            }
            parentList.forEachIndexed { _, colorRow ->
                ColorRow(
                    rowElementsCount = rowElementsCount,
                    colorRow = colorRow,
                    colorIntensity = colorIntensity,
                    defaultColor = defaultColor,
                    unSelectedSize = unSelectedSize,
                    selectedSize = selectedSize
                ) { colorRow, color ->
                    subColorsRowVisibility =
                        subColorsRowVisibility == false || defaultColor.value != color.value
                    defaultColor = color
                    defaultRow = colorRow
                    if (subColorsRowVisibility)
                        clickedColor(color)
                }
            }
        }
    }
}

private const val defaultColorIntensity = 5
private const val defaultRowElementsCount = 8
private val defaultUnSelectedSize = 26.dp
private val defaultSelectedSize = 36.dp

//Kept public to be used by host app to preSelected color
val defaultSelectedColor = colorArray[6][defaultColorIntensity]
