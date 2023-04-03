package com.san.news.presentation.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.san.news.presentation.NewsViewModel
import com.san.news.presentation.component.NewsItem

@Composable
fun AllNewsPage(
    modifier: Modifier = Modifier, navController: NavController, viewModel: NewsViewModel
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val list by viewModel.newsList.collectAsState()
    val last by viewModel.lastPageReached.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), text = list.size.toString()
        )

        LazyColumn(state = listState) {
            items(list.size) { i ->
                if (i >= list.size - 1) {
                    viewModel.fetchNews()
                    LaunchedEffect(key1 = last) {
                        if (last) Toast.makeText(context, "Reached Last Item", Toast.LENGTH_SHORT).show()
                    }
                }
                NewsItem(news = list[i])
            }
            item {
                if (isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun NewsPagePagePreview() {
    AllNewsPage(navController = rememberNavController(), viewModel = viewModel())
}