package com.san.news.presentation.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.san.core.utils.isScrolledToTheEnd
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
    val loader by viewModel.isLoading.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        if (loader) {
            CircularProgressIndicator(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(30.dp)
            )
        }
        if (last) {
            Toast.makeText(context, "Reached Last Item", Toast.LENGTH_SHORT).show()
        }
        LazyColumn(state = listState) {
            items(list) {
                NewsItem(news = it)
            }
        }
    }

    /*if (listState.isScrolledToTheEnd(list.size).value) {
        viewModel.fetchNews()
    }*/

}

@Preview
@Composable
fun NewsPagePagePreview() {
    AllNewsPage(navController = rememberNavController(), viewModel = viewModel())
}