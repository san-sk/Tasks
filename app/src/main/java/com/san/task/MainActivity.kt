package com.san.task

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.san.canvas.presentation.CanvasActivity
import com.san.core.base.BaseActivity
import com.san.news.presentation.NewsActivity
import com.san.task.data.Features
import com.san.task.ui.theme.TasksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column(Modifier.padding(it)) {
                        HomePage {
                            this@MainActivity.openActivity(it) {
                                Toast.makeText(this@MainActivity, "Not found..", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomePage(onClick: (feature: Features) -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Features App",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Click to view the respective feature App",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            items(Features.entries) {
                FeatureItem(it) {
                    onClick(it)
                }
            }
        }
    }

}

@Composable
fun FeatureItem(
    feature: Features,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = feature.name,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(10.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

fun Activity.openActivity(feature: Features, callBack: () -> Unit) {
    val intent = when (feature) {
        Features.CANVAS -> Intent(this, CanvasActivity::class.java)
        Features.NEWS -> Intent(this, NewsActivity::class.java)
        Features.BARD -> null
    }
    kotlin.runCatching { startActivity(intent) }.getOrElse {
        callBack()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TasksTheme {
        HomePage(onClick = {})
    }
}