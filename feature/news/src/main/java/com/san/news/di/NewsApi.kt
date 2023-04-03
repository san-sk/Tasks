package com.san.news.di

import com.san.news.data.entity.NewsBaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("newslist/{page}.json")
    suspend fun getNews(@Path("page") page: Int = 0): NewsBaseResponse
}