package com.guizaotech.listaseries.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.pagedListDataSource.ShowDataSourceFactory
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient

class HomeViewModel(
    application: Application,
    webClient: WebClient
) : ViewModel() {
    private var showPagedList: LiveData<PagedList<Show>>? = null

    init {
        val showFactory =  ShowDataSourceFactory(webClient, application)

        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(30)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .build()

        showPagedList = LivePagedListBuilder<Int, Show>(showFactory, config)
            .build()

    }

    fun getShowPagedList() : LiveData<PagedList<Show>>? = showPagedList

}