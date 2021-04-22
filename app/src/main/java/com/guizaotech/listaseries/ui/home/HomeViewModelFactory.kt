package com.guizaotech.listaseries.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient

class HomeViewModelFactory(
    private val webClient: WebClient,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(application, webClient) as T
    }

}