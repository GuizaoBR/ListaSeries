package com.guizaotech.listaseries.pagedListDataSource

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.repository.Repository

class ShowDataSource(
        private val repository: Repository,
        private val search: String
):   PageKeyedDataSource<Int, Show>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Show>
    ) {
        val page: Int = 1

        if (search == ""){

            repository.getAllShow(page = page,
                    success = { result ->
                        //liveData.value = Resource(data = result)
                        callback.onResult(
                                result!!,  // List of data items
                                0,  // Position of first item
                                400,  // Total number of items that can be fetched from api
                                null,  // Previous page. `null` if there's no previous page
                                page // Next Page (Used at the next request). Return `null` if this is the last page.
                        )
                    },
                    failure = { result ->
                        var erro = result
                    })
        } else {
            repository.showSeach(search,
                    success = { result ->
                //liveData.value = Resource(data = result)
                callback.onResult(
                        result!!,  // List of data items
                        0,  // Position of first item
                        400,  // Total number of items that can be fetched from api
                        null,  // Previous page. `null` if there's no previous page
                        page // Next Page (Used at the next request). Return `null` if this is the last page.
                )
            },
                    failure = { result ->
                        var erro = result
                    })
        }



    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Show>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Show>) {
        val page: Int = params.key
        if (search == ""){

            repository.getAllShow(page = page,
                    success = { result ->
                        //liveData.value = Resource(data = result)

                        callback.onResult(
                                result!!,  // List of data items
                                // Next Page key (Used at the next request). Return `null` if this is the last page.
                                page + 1
                        )
                    },
                    failure = { result ->
                        //liveData.value = Resource(data = null, error = result)
                    })
        } else {
            repository.showSeach(search,
                    success = { result ->
                        //liveData.value = Resource(data = result)

                        callback.onResult(
                                result!!,  // List of data items
                                // Next Page key (Used at the next request). Return `null` if this is the last page.
                                page + 1
                        )
                    },
                    failure = { result ->
                        //liveData.value = Resource(data = null, error = result)
                    })
        }
    }
}