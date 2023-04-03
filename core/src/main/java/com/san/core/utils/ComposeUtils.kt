package com.san.core.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf

fun LazyListState.isScrolledToTheEnd(size: Int): State<Boolean> {
    return derivedStateOf { layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1 }
}
