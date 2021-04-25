package com.guizaotech.listaseries.pagedListDataSource

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.repository.Repository

class ShowDataSourceFactory(
        private val repository: Repository,
        private val search: String
): DataSource.Factory<Int, Show>() {

    private var showDataSource: ShowDataSource? = null
    private val mutableLiveData: MutableLiveData<ShowDataSource> =  MutableLiveData<ShowDataSource>()

    override fun create(): DataSource<Int, Show> {
        showDataSource = ShowDataSource(repository, search)
        mutableLiveData.postValue(showDataSource!!)
        return showDataSource!!
    }


}