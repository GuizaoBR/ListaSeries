package com.guizaotech.listaseries.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
import com.guizaotech.listaseries.repository.Repository
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient
import com.guizaotech.listaseries.ui.MESSAGE_ERROR_GENERIC
import com.guizaotech.listaseries.ui.adapter.PagingShowAdpater
import com.guizaotech.listaseries.ui.extesion.showToastError

class HomeFragment : Fragment() {

    private var shows: PagedList<Show>? = null

    private  var binding: FragmentHomeBinding? = null

    private val adapter by lazy {
        PagingShowAdpater()
    }


    private val viewModel by lazy {
        val webClient = WebClient()
        val repository = Repository(webClient)
        val factory = HomeViewModelFactory(repository, this.requireActivity().application)
        val provider = ViewModelProviders.of(this, factory)
        provider.get(HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        getAllShow()

        binding = FragmentHomeBinding.inflate(layoutInflater)

        configSearchEditText()
        return binding!!.root
    }

    private fun getAllShow() {
        viewModel.getShows()!!.observe(viewLifecycleOwner, Observer { liveData ->
            liveData?.let {
                shows = it
                showOnRecyclerView()

            }
            if (shows == null) {
                showToastError(MESSAGE_ERROR_GENERIC)
            }

        })
    }

    private fun configSearchEditText() {
        editTextSearchTextChanged()
        editTextSearchAction()
    }

    private fun editTextSearchAction() {
        binding?.editTextSearch?.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getSearchedShow(textView)
                true
            } else {

                false
            }
        }
    }

    private fun editTextSearchTextChanged() {
        binding?.editTextSearch?.addTextChangedListener {
            if (it.isNullOrEmpty()) {
                getAllShow()
            }
        }
    }

    private fun getSearchedShow(textView: TextView) {
        viewModel.getShows(textView.text.toString())!!.observe(viewLifecycleOwner, Observer { liveData ->
            liveData?.let {
                shows = it
                showOnRecyclerView()

            }

        })
    }

    private fun showOnRecyclerView() {
        adapter.submitList(shows)
        binding!!.litstShowRecyclerview.adapter = adapter
        binding!!.litstShowRecyclerview.itemAnimator = DefaultItemAnimator()

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