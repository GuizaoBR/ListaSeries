package com.guizaotech.listaseries.ui.showDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guizaotech.listaseries.repository.Repository

class DetailShowViewModelFactory(
        private val repository: Repository,
        private val id: Long
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailShowViewModel(repository, id) as T
    }
}