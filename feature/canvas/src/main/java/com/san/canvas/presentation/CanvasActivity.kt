package com.san.canvas.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.san.canvas.presentation.screen.CanvaScreen
import com.san.canvas.presentation.screen.CanvasScreen
import com.san.canvas.presentation.screen.CanvasViewModel
import com.san.canvas.presentation.theme.TasksTheme
import com.san.canvas.utils.activityChooser
import com.san.canvas.utils.checkAndAskPermission
import com.san.canvas.utils.saveImageOnCache
import com.san.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
@AndroidEntryPoint
class CanvasActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<CanvasViewModel>()
                    CanvaScreen(viewModel) {
                        checkAndAskPermission {
                            CoroutineScope(Dispatchers.IO).launch {
                                val uri = saveImageOnCache(it)
                                withContext(Dispatchers.Main) {
                                    kotlin.runCatching {
                                        startActivity(activityChooser(uri))
                                    }.getOrElse {
                                        Timber.tag("Share").e(it, it.localizedMessage)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TasksTheme {
        CanvasScreen() {

        }
    }
}