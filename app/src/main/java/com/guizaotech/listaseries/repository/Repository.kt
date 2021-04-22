package com.guizaotech.listaseries.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient

class Repository(
    private val webClient: WebClient
){

    fun getShow(id: Long): LiveData<Show?>{
        val liveData: MutableLiveData<Show?> = MutableLiveData<Show?>()
        webClient.getShow(id, success = {show ->
            liveData.value = show

        }, failure = {

        })
        return liveData
    }
}