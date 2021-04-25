package com.guizaotech.listaseries.ui.showDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.appbar.AppBarLayout
import com.guizaotech.listaseries.databinding.ActivityDetailShowBinding
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.repository.Repository
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient
import com.guizaotech.listaseries.ui.MESSAGE_ERROR_GENERIC
import com.guizaotech.listaseries.ui.SHOW_ID
import com.guizaotech.listaseries.ui.adapter.DetailShowPagerAdapter
import com.guizaotech.listaseries.ui.extesion.showToastError
import com.squareup.picasso.Picasso
import java.lang.Exception

class DetailShowActivity : AppCompatActivity() {


    private val showId: Long by lazy {
        intent.getLongExtra(SHOW_ID, 0)
    }
    private var show: Show? = null

    private var binding: ActivityDetailShowBinding? = null



    private val viewModel: DetailShowViewModel by viewModels {
        val webClient = WebClient()
        val repository = Repository(webClient)
        DetailShowViewModelFactory(repository,  showId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailShowBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        viewModel.show.observe(this, Observer {result ->
            result.data?.let {
                show = it
                fillDataOnActivity()

            }
            result.error?.let {
                showToastError(MESSAGE_ERROR_GENERIC)
            }
        })
//        AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
//            if(Math.abs(verticalOffset) == appBarLayout.totalScrollRange ){
//
//            } else {
//
//            }
//        }
        binding?.appBar?.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if(Math.abs(verticalOffset) == appBarLayout.totalScrollRange ){
                //collapsed
                binding?.textViewGenres?.visibility = View.INVISIBLE
            } else {
                //expanded
                binding?.textViewGenres?.visibility = View.VISIBLE
            }
        })


        val sectionsPagerAdapter = DetailShowPagerAdapter(this, fm = supportFragmentManager)

        binding!!.viewPager.adapter = sectionsPagerAdapter

        binding!!.tabs?.tabs?.setupWithViewPager(binding!!.viewPager)
        binding!!.tabs23?.tabs?.setupWithViewPager(binding!!.viewPager)
        setSupportActionBar(binding!!.toolbar)
    }

    private fun fillDataOnActivity() {
        try {

            Picasso.get().load(show!!.image.original)
                    .fit()
                    .into(binding!!.imageView3)
        } catch (ex: Exception){

        }

        binding!!.toolbarLayout.title = show!!.name
        binding!!.textViewGenres.text = show!!.genres.joinToString(", ")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }



}


