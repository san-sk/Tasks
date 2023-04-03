package com.san.news.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.san.news.presentation.NewsViewModel
import com.san.news.presentation.pages.AllNewsPage
import com.san.news.presentation.pages.NewsHomePage

@Composable
fun NewsNavigation(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = "newsHomePge"
    ) {

        /* composable("allNews") {
             val viewModel = hiltViewModel<NewsViewModel>()
             AllNewsPage(navController = navController, viewModel = viewModel)
         }*/

        composable("newsHomePge") {
            val viewModel = hiltViewModel<NewsViewModel>()
            NewsHomePage(navController = navController, viewModel = viewModel)
        }

    }
}