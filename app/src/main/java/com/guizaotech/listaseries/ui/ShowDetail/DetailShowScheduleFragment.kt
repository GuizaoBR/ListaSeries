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
import com.guizaotech.listaseries.databinding.FragmentDetailShowScheduleBinding
import com.guizaotech.listaseries.databinding.FragmentDetailShowSummaryBinding
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.repository.Repository
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient

class DetailShowScheduleFragment : Fragment() {
    private val showId: Long by lazy {
        this.requireActivity().intent.getLongExtra("showId", 0)
    }
    private var show: Show? = null

    private var binding: FragmentDetailShowScheduleBinding? = null



private val viewModel: DetailShowViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailShowScheduleBinding.inflate(layoutInflater)
        viewModel.show.observe(viewLifecycleOwner, Observer {
            show = it
            if (show != null) {
               binding?.textViewTime?.text = show?.schedule?.time
               binding?.textViewDays?.text = show?.schedule?.days?.joinToString(", ")
            }
        })

        return binding!!.root
    }


}