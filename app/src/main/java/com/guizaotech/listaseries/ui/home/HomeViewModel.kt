package com.guizaotech.listaseries.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.pagedListDataSource.ShowDataSourceFactory
import com.guizaotech.listaseries.repository.Repository

class HomeViewModel(
    private val application: Application,
    private val repository: Repository
) : ViewModel() {
    private var showPagedList: LiveData<PagedList<Show>>? = null

    init {
        getShows()

    }

    fun getShows(search: String = ""): LiveData<PagedList<Show>>? {
        val showFactory = ShowDataSourceFactory(repository, application, search)

        val config: PagedList.Config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build()

        showPagedList = LivePagedListBuilder<Int, Show>(showFactory, config)
                .build()
        return showPagedList
    }

    fun getShowPagedList() : LiveData<PagedList<Show>>? = showPagedList



}