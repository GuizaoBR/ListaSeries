package com.guizaotech.listaseries.ui.showDetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.repository.Repository

class DetailShowEpisodesViewModel(application: Application,
                                  repository: Repository,
                                  idShow: Long
) : ViewModel() {
    val episodes: LiveData<List<Episode>?> = repository.getEpisodes(idShow = idShow)

  //  fun getShowPagedList() : LiveData<PagedList<Show>>? = showPagedList
}