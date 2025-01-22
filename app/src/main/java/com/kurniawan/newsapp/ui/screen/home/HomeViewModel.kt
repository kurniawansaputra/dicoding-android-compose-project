package com.kurniawan.newsapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurniawan.newsapp.data.source.remote.NewsRepository
import com.kurniawan.newsapp.data.source.remote.response.ArticlesItem
import com.kurniawan.newsapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<ArticlesItem>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ArticlesItem>>> = _uiState

    fun getTopHeadlines(source: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = newsRepository.getNews(source, apiKey)
                _uiState.value = UiState.Success(response.articles)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

