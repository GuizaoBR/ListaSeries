package com.guizaotech.listaseries.ui.ShowDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.guizaotech.listaseries.R
import com.guizaotech.listaseries.databinding.ActivityDetailShowBinding
import com.guizaotech.listaseries.databinding.FragmentHomeBinding
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.repository.Repository
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient
import com.guizaotech.listaseries.ui.adapter.DetailShowPagerAdapter
import com.guizaotech.listaseries.ui.adapter.PagingShowAdpater
import com.guizaotech.listaseries.ui.home.HomeViewModel
import com.guizaotech.listaseries.ui.home.HomeViewModelFactory
import com.squareup.picasso.Picasso

class DetailShowActivity : AppCompatActivity() {


    private val showId: Long by lazy {
        intent.getLongExtra("showId", 0)
    }
    private var show: Show? = null

    private var binding: ActivityDetailShowBinding? = null


    private val viewModel by lazy {
        val webClient = WebClient()
        val repository = Repository(webClient)
        val factory = DetailShowViewModelFactory(repository, this.application, showId)
        val provider = ViewModelProviders.of(this, factory)
        provider.get(DetailShowViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailShowBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        viewModel.show.observe(this, Observer {
            show = it
            if (show != null) {

                Picasso.get().load(show!!.image.original)
                        .fit()
                        .into(binding!!.imageView3)

                binding!!.toolbarLayout.title = show!!.name
            }
        })


        val sectionsPagerAdapter = DetailShowPagerAdapter(this, fm = supportFragmentManager)

        binding!!.viewPager.adapter = sectionsPagerAdapter

        binding!!.tabs.setupWithViewPager(binding!!.viewPager)
        setSupportActionBar(binding!!.toolbar)
    }


//irei usar para implementar o botão de inserir favoritos futuramente
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
}


