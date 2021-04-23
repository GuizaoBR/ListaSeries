package com.guizaotech.listaseries.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.guizaotech.listaseries.R
import com.guizaotech.listaseries.databinding.FragmentHomeBinding
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient
import com.guizaotech.listaseries.ui.adapter.PagingShowAdpater

class HomeFragment : Fragment() {

    private var shows: PagedList<Show>? = null

    private  var binding: FragmentHomeBinding? = null

    private val adapter by lazy {
        PagingShowAdpater()
    }


    private val viewModel by lazy {
        val webClient = WebClient()
        val factory = HomeViewModelFactory(webClient, this.requireActivity().application)
        val provider = ViewModelProviders.of(this, factory)
        provider.get(HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getShowPagedList()!!.observe(viewLifecycleOwner, Observer { liveData ->
            liveData?.let {
                shows = it
                showOnRecyclerView()

            }
        })
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding!!.root
    }

    private fun showOnRecyclerView() {
        adapter.submitList(shows)
        binding!!.litstShowRecyclerview.adapter = adapter
        binding!!.litstShowRecyclerview.itemAnimator = DefaultItemAnimator()
//        binding!!.litstShowRecyclerview.layoutManager = LinearLayoutManager(this.context)

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding!!.litstShowRecyclerview.layoutManager = GridLayoutManager(this.context, 2)
        } else {
            binding!!.litstShowRecyclerview.layoutManager = GridLayoutManager(this.context, 4)
        }

        adapter.notifyDataSetChanged()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}