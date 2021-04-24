package com.guizaotech.listaseries.ui.showDetail

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.guizaotech.listaseries.databinding.FragmentDetailShowEpisodesBinding
import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.repository.Repository
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient
import com.guizaotech.listaseries.ui.adapter.ListEpisodesAdapter

class DetailShowEpisodesFragment : Fragment() {
    private val showId: Long by lazy {
        this.requireActivity().intent.getLongExtra("showId", 0)
    }
    private var episodes: List<Episode>? = null

    private var binding: FragmentDetailShowEpisodesBinding? = null


    private val viewModel by lazy {
        val webClient = WebClient()
        val repository = Repository(webClient)
        val factory = DetailShowEpisodesViewModelFactory(repository, this.requireActivity().application, showId)
        val provider = ViewModelProviders.of(this, factory)
        provider.get(DetailShowEpisodesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailShowEpisodesBinding.inflate(layoutInflater)

        viewModel.episodes.observe(viewLifecycleOwner, Observer {
            episodes = it
            if (episodes != null) {
                createSpinner()
                createList()
            }
        })

        return binding!!.root
    }

    private fun createList() {
        val seasonSelected = binding?.spinnerSeason?.selectedItem.toString().split("ª")[0].toInt()
        val listEpisodesBySeason = episodes?.filter { episode -> episode.season == seasonSelected }
        val adapter = ListEpisodesAdapter(listEpisodesBySeason!!)
        binding?.listtEpisodesRecyclerview?.adapter = adapter
        binding?.listtEpisodesRecyclerview?.itemAnimator = DefaultItemAnimator()
        binding?.listtEpisodesRecyclerview?.layoutManager = LinearLayoutManager(this.requireContext())
        adapter.notifyDataSetChanged()
    }

    private fun createSpinner() {
        val listSeason: Array<String> = episodes?.distinctBy { episode ->
            episode.season
        }?.map { episode ->
            "${episode.season}ª Temporada"
        }?.toTypedArray()!!


        val spinnerAdapter = ArrayAdapter(this.requireContext(), R.layout.simple_spinner_item, listSeason)
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding?.spinnerSeason?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                createList()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding?.spinnerSeason?.setSelection(0)
            }

        }
        binding?.spinnerSeason?.adapter = spinnerAdapter


    }


}