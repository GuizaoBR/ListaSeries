package com.guizaotech.listaseries.ui.ShowDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guizaotech.listaseries.repository.Repository

class DetailShowEpisodesViewModelFactory(
        private val repository: Repository,
        private val application: Application,
        private val id: Long
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailShowEpisodesViewModel(application, repository, id) as T
    }
}