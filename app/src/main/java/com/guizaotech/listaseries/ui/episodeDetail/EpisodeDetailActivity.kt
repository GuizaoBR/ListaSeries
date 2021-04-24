package com.guizaotech.listaseries.ui.episodeDetail

import android.os.Bundle
import android.text.Html
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.guizaotech.listaseries.R
import com.guizaotech.listaseries.databinding.ActivityDetailShowBinding
import com.guizaotech.listaseries.databinding.ActivityEpisodeDetailBinding
import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.model.Show
import com.guizaotech.listaseries.repository.Repository
import com.guizaotech.listaseries.retrofit.service.webClient.WebClient
import com.guizaotech.listaseries.ui.showDetail.DetailShowViewModel
import com.squareup.picasso.Picasso

class EpisodeDetailActivity : AppCompatActivity() {
    private val showId: Long by lazy {
        intent.getLongExtra("episodeId", 0)
    }
    private var episode: Episode? = null

    private var binding: ActivityEpisodeDetailBinding? = null

        private val viewModel by lazy {
        val webClient = WebClient()
        val repository = Repository(webClient)
        val factory = EpisodeDetailViewModelFactory(repository, showId)
        val provider = ViewModelProviders.of(this, factory)
        provider.get(EpisodeDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodeDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        viewModel.episode.observe(this, Observer {
            episode = it
            if (episode != null) {
                binding?.toolbarLayout?.title = "T${episode?.season}E${episode?.number} - ${episode?.name}"
                binding?.textSummary?.textViewSumary?.text = Html.fromHtml(episode!!.summary).toString()
                try {
                    if(episode?.image?.original != ""){
                        Picasso.get().load(episode?.image?.original)
                                .fit()
                                .into(binding?.imageView);
                    } else if (episode?.image?.medium != ""){
                        Picasso.get().load(episode?.image?.medium)
                                .fit()
                                .into(binding?.imageView)
                    }
                }
                catch (ex: Exception){

                }
            }
        })

    }
}