package com.san.canvas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.san.canvas.screen.CanvasScreen
import com.san.canvas.ui.theme.TasksTheme
import com.san.canvas.utils.activityChooser
import com.san.canvas.utils.checkAndAskPermission
import com.san.canvas.utils.saveImageOnCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CanvasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CanvasScreen {
                        checkAndAskPermission {
                            CoroutineScope(Dispatchers.IO).launch {
                                val uri = saveImageOnCache(it)
                                withContext(Dispatchers.Main) {
                                    kotlin.runCatching {
                                        startActivity(activityChooser(uri))
                                    }.getOrElse {
                                        Log.e("Share", it.localizedMessage, it)
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
        CanvasScreen {

        }
    }
}