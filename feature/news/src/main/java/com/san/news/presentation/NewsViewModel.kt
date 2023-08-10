package com.san.news.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san.core.utils.Resource
import com.san.news.data.entity.NewsBaseResponse
import com.san.news.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val useCase: NewsUseCase) : ViewModel() {

    private val _newsList =
        MutableStateFlow<List<NewsBaseResponse.Data.Newslist>>(listOf())
    val newsList = _newsList.asStateFlow()

    private var currentPage = 0

    var isLoading = MutableStateFlow(false)
    var lastPageReached = MutableStateFlow(false)

    init {
        fetchNews()
    }

    fun fetchNews() {
        if (isLoading.value || lastPageReached.value) return

        viewModelScope.launch {
            useCase.invoke(currentPage)
                .onStart { isLoading.value = true }
                .onCompletion {
                    isLoading.value = false
                }
                .collectLatest {
                    Timber.e("news-on_collect")
                    when (it) {

                        is Resource.Error -> {
                        }

                        is Resource.Loader -> {

                        }

                        is Resource.Success -> {
                            lastPageReached.value = it.result.data?.nextItems == 0
                            it.result.data?.newslist?.let { news ->
                                _newsList.value = newsList.value + news
                            }
                            if(!lastPageReached.value) currentPage++
                        }

                        else -> {
                            Timber.e("news-on_collect_else")
                        }
                    }
                }

        }
    }
}