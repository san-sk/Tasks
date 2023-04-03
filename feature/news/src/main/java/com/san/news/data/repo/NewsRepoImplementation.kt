package com.san.news.data.repo

import android.content.Context
import com.san.core.utils.Resource
import com.san.core.utils.networkOnlyResource
import com.san.news.data.entity.NewsBaseResponse
import com.san.news.di.NewsApi
import com.san.news.domain.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepoImplementation @Inject constructor(
    private val context: Context,
    private val api: NewsApi
) : NewsRepository {

    override suspend fun fetchNews(page: Int): Flow<Resource<NewsBaseResponse>> {
        return networkOnlyResource(context) {
            api.getNews(page)
        }
    }

}