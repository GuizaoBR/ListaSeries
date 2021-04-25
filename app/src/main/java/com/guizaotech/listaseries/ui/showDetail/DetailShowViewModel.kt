package com.guizaotech.listaseries.ui.showDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.guizaotech.listaseries.model.Resource
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.repository.Repository

class DetailShowViewModel(
                          repository: Repository,
                          idShow: Long
) : ViewModel() {
    val show: LiveData<Resource<Show?>> = repository.getShow(id = idShow)

}