package com.san.news.domain.repo

import com.san.core.utils.Resource
import com.san.news.data.entity.NewsBaseResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun fetchNews(page: Int = 0): Flow<Resource<NewsBaseResponse>>
}