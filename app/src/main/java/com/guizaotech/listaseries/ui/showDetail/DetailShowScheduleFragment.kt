package com.guizaotech.listaseries.ui.showDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.guizaotech.listaseries.databinding.FragmentDetailShowScheduleBinding
import com.guizaotech.listaseries.model.Show

class DetailShowScheduleFragment : Fragment() {

    private var show: Show? = null

    private var binding: FragmentDetailShowScheduleBinding? = null

private val viewModel: DetailShowViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailShowScheduleBinding.inflate(layoutInflater)
        viewModel.show.observe(viewLifecycleOwner, Observer { result ->
            result.data?.let {
                show = it
                fillDataOnFragment()
            }
        })

        return binding!!.root
    }

    private fun fillDataOnFragment() {
        binding?.textViewTime?.text = show?.schedule?.time
        binding?.textViewDays?.text = show?.schedule?.days?.joinToString(", ")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}