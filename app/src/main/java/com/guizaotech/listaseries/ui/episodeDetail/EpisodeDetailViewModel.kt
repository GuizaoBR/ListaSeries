package com.guizaotech.listaseries.ui.episodeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.repository.Repository

class EpisodeDetailViewModel(
        private val repository: Repository,
        private val id: Long
): ViewModel() {
    val episode: LiveData<Episode?> = repository.getEpisode(id)
}