package com.guizaotech.listaseries.ui.ShowDetail

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.guizaotech.listaseries.databinding.FragmentDetailShowSummaryBinding
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.repository.Repository
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient

class DetailShowSummaryFragment : Fragment() {
    private val showId: Long by lazy {
        this.requireActivity().intent.getLongExtra("showId", 0)
    }
    private var show: Show? = null

    private var binding: FragmentDetailShowSummaryBinding? = null


//    private val viewModel by lazy {
//        val webClient = WebClient()
//        val repository = Repository(webClient)
//        val factory = DetailShowViewModelFactory(repository, this.requireActivity().application, showId)
//        val provider = ViewModelProviders.of(this, factory)
//        provider.get(DetailShowViewModel::class.java)
//    }
private val viewModel: DetailShowViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailShowSummaryBinding.inflate(layoutInflater)
        viewModel.show.observe(viewLifecycleOwner, Observer {
            show = it
            if (show != null) {
               binding!!.textViewSumary.text = Html.fromHtml(show!!.summary).toString()
            }
        })

        return binding!!.root
    }


}