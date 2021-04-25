package com.guizaotech.listaseries.retrofit.service.webClient

import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.model.SearchShow
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.retrofit.AppRetrofit
import com.guizaotech.listaseries.retrofit.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val FAILURE = "Requisição não sucedida"

class WebClient (
    val service: ApiService = AppRetrofit().service
)  {
    private fun <T> executeApi(
        call: Call<T>,
        success: (item: T?) -> Unit,
        failure: (error: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    failure(FAILURE)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                failure(t.message)
            }
        })
    }

    fun getAllShow(
        page: Int,
        success: (shows: List<Show>?) -> Unit,
        failure: (error: String?) -> Unit
    ) {
        executeApi(
            service.getShows(page),
            success,
            failure
        )
    }

    fun getShow(
            id: Long,
            success: (shows: Show?) -> Unit,
            failure: (error: String?) -> Unit
    ) {
        executeApi(
                service.getShow(id),
                success,
                failure
        )
    }

    fun getEpisodes(
            idShow: Long,
            success: (episodes: List<Episode>?) -> Unit,
            failure: (error: String?) -> Unit
    ) {
        executeApi(
                service.getEpisodes(idShow),
                success,
                failure
        )
    }

    fun getEpisode(id: Long, sucess: (episode: Episode?) -> Unit, failure: (error: String?) -> Unit) {
        executeApi(
                service.getEpisode(id),
                sucess,
                failure
        )
    }

    fun showSearch(showName: String, sucess: (listShow: List<SearchShow>?) -> Unit, failure: (error: String?) -> Unit) {
        executeApi(
                service.getShowsSearch(showName),
                sucess,
                failure
        )
    }


}