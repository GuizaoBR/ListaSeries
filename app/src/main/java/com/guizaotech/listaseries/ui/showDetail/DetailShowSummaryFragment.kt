package com.guizaotech.listaseries.ui.showDetail

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.guizaotech.listaseries.databinding.FragmentDetailShowSummaryBinding
import com.guizaotech.listaseries.model.Show

class DetailShowSummaryFragment : Fragment() {
    private val showId: Long by lazy {
        this.requireActivity().intent.getLongExtra("showId", 0)
    }
    private var show: Show? = null

    private var binding: FragmentDetailShowSummaryBinding? = null



private val viewModel: DetailShowViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailShowSummaryBinding.inflate(layoutInflater)
        viewModel.show.observe(viewLifecycleOwner, Observer { result ->
            result.data?.let {
                show = it
                fillDataOnFragment()
            }
        })

        return binding!!.root
    }

    private fun fillDataOnFragment() {
        binding!!.textViewSumary.text = if(show!!.summary != "") Html.fromHtml(show!!.summary).toString() else "Não foi encontrado resumo desse programa"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}