package com.guizaotech.listaseries.pagedListDataSource

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient

class ShowDataSourceFactory(
    private val webClient: WebClient,
    private val application: Application
): DataSource.Factory<Int, Show>() {

    private var showDataSource: ShowDataSource? = null
    private val mutableLiveData: MutableLiveData<ShowDataSource> =  MutableLiveData<ShowDataSource>()

    override fun create(): DataSource<Int, Show> {
        showDataSource = ShowDataSource(webClient, application)
        mutableLiveData.postValue(showDataSource!!)
        return showDataSource!!
    }


}