package com.guizaotech.listaseries.ui.episodeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.model.Resource
import com.guizaotech.listaseries.repository.Repository

class EpisodeDetailViewModel(
        repository: Repository,
        id: Long
): ViewModel() {
    val episode: LiveData<Resource<Episode?>> = repository.getEpisode(id)
}