package com.guizaotech.listaseries.ui.episodeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guizaotech.listaseries.repository.Repository

class EpisodeDetailViewModelFactory(
        private val repository: Repository,
        private val id: Long
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpisodeDetailViewModel(repository, id) as T
    }
}