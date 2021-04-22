package com.guizaotech.listaseries.retrofit.service.webClient

import androidx.paging.PageKeyedDataSource
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
    private fun <T> excuteApi(
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
        failure: (erro: String?) -> Unit
    ) {
        excuteApi(
            service.getShows(page),
            success,
            failure
        )
    }




}