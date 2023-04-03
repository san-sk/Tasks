package com.san.news.domain.usecase

import com.san.news.domain.repo.NewsRepository

class NewsUseCase(private val repo: NewsRepository) {

    suspend operator fun invoke(page: Int) = repo.fetchNews(page)
}