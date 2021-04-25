package com.guizaotech.listaseries.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.model.Resource
import com.guizaotech.listaseries.model.SearchShow
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient

class Repository(
    private val webClient: WebClient
){

    fun getAllShow(page: Int,
                   success: (shows: List<Show>?) -> Unit,
                   failure: (error: String?) -> Unit) {
        webClient.getAllShow(page, success, failure)

    }

    fun showSeach(showName: String,
                   success: (shows: List<SearchShow>?) -> Unit,
                   failure: (error: String?) -> Unit) {
        webClient.showSearch(showName, success, failure)

    }

    fun getShow(id: Long): LiveData<Resource<Show?>>{
        val liveData: MutableLiveData<Resource<Show?>> = MutableLiveData<Resource<Show?>>()
        webClient.getShow(id, success = {show ->
            liveData.value = Resource(data = show)

        }, failure = {
            liveData.value = Resource(data = null, error = it)
        })
        return liveData
    }


    fun getEpisodes(idShow: Long): LiveData<Resource<List<Episode>?>>{
        val liveData: MutableLiveData<Resource<List<Episode>?>> = MutableLiveData<Resource<List<Episode>?>>()
        webClient.getEpisodes(idShow, success = {listEpisode ->
            liveData.value = Resource(data = listEpisode)

        }, failure = { error ->
            liveData.value = Resource(data = null, error = error )
        })
        return liveData
    }

    fun getEpisode(id: Long): LiveData<Resource<Episode?>> {
        val liveData: MutableLiveData<Resource<Episode?>> = MutableLiveData<Resource<Episode?>>()
        webClient.getEpisode(id, sucess = { episode ->
            liveData.value = Resource(data = episode)
        }, failure = {
            liveData.value = Resource(data = null, error = it)
        })
        return  liveData
    }


}