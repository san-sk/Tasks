package com.san.canvas.presentation.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.san.canvas.data.CanvasScreenState
import com.san.canvas.data.events.CanvasUIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CanvasViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(CanvasScreenState())
    val state: StateFlow<CanvasScreenState> = _state

    fun updateUIEvent(event: CanvasUIEvents) {
        _state.value = when (event) {
            is CanvasUIEvents.UpdateBgColor -> _state.value.copy(backgroundColor = event.color.value)
            is CanvasUIEvents.UpdateColor -> _state.value.copy(currentColor = event.color.value)
            is CanvasUIEvents.UpdateColorBarVisibility -> _state.value.copy(colorBarVisibility = event.isVisible)
            is CanvasUIEvents.UpdateColorIsBG -> _state.value.copy(colorIsBg = event.isVisible)
            is CanvasUIEvents.UpdateRedoVisibility -> _state.value.copy(redoVisibility = event.isVisible)
            is CanvasUIEvents.UpdateSize -> _state.value.copy(currentSize = event.size)
            is CanvasUIEvents.UpdateSizeVisibility -> _state.value.copy(sizeBarVisibility = event.isVisible)
            is CanvasUIEvents.UpdateUndoVisibility -> _state.value.copy(undoVisibility = event.isVisible)
        }
        //savedStateHandle["canvas"] = state.value
    }

}

