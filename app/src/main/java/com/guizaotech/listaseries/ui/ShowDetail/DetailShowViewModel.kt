package com.guizaotech.listaseries.ui.ShowDetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.repository.Repository

class DetailShowViewModel(application: Application,
                          repository: Repository,
                          idShow: Long
) : ViewModel() {
    val show: LiveData<Show?> = repository.getShow(id = idShow)

  //  fun getShowPagedList() : LiveData<PagedList<Show>>? = showPagedList
}