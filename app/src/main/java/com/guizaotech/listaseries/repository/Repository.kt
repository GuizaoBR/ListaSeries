package com.guizaotech.listaseries.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.guizaotech.listaseries.model.Episode
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
                   success: (shows: List<Show>?) -> Unit,
                   failure: (error: String?) -> Unit) {
        webClient.showSearch(showName, success, failure)

    }

    fun getShow(id: Long): LiveData<Show?>{
        val liveData: MutableLiveData<Show?> = MutableLiveData<Show?>()
        webClient.getShow(id, success = {show ->
            liveData.value = show

        }, failure = {

        })
        return liveData
    }


    fun getEpisodes(idShow: Long): LiveData<List<Episode>?>{
        val liveData: MutableLiveData<List<Episode>?> = MutableLiveData<List<Episode>?>()
        webClient.getEpisodes(idShow, success = {listEpisode ->
            liveData.value = listEpisode

        }, failure = {

        })
        return liveData
    }

    fun getEpisode(id: Long): LiveData<Episode?> {
        val liveData: MutableLiveData<Episode?> = MutableLiveData<Episode?>()
        webClient.getEpisode(id, sucess = {episode ->
            liveData.value = episode
        }, failure = {

        })
        return  liveData
    }


}