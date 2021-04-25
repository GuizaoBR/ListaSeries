package com.guizaotech.listaseries.ui.showDetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.model.Resource
import com.guizaotech.listaseries.repository.Repository

class DetailShowEpisodesViewModel(
                                  repository: Repository,
                                  idShow: Long
) : ViewModel() {
    val episodes: LiveData<Resource<List<Episode>?>> = repository.getEpisodes(idShow = idShow)

  //  fun getShowPagedList() : LiveData<PagedList<Show>>? = showPagedList
}