package com.guizaotech.listaseries.retrofit.service

import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.model.SearchShow
import com.guizaotech.listaseries.model.Show
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("shows")
    fun getShows(@Query("page") page: Int): Call<List<Show>>

    @GET("shows/{id}")
    fun getShow(@Path("id") id: Long): Call<Show>

    @GET("/shows/{id}/episodes")
    fun getEpisodes(@Path("id") id: Long): Call<List<Episode>>

    @GET("/episodes/{id}")
    fun getEpisode(@Path("id") id: Long): Call<Episode>

    @GET("search/shows")
    fun getShowsSearch(@Query("q") search: String): Call<List<SearchShow>>

}