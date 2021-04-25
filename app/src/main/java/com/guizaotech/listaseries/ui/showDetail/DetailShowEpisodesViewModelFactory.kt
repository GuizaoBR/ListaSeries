package com.guizaotech.listaseries.ui.showDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guizaotech.listaseries.repository.Repository

class DetailShowEpisodesViewModelFactory(
        private val repository: Repository,
        private val id: Long
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailShowEpisodesViewModel(repository, id) as T
    }
}