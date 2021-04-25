package com.guizaotech.listaseries.ui.adapter

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guizaotech.listaseries.databinding.EpisodeItemBinding
import com.guizaotech.listaseries.model.Episode
import com.guizaotech.listaseries.ui.EPISODE_ID
import com.guizaotech.listaseries.ui.episodeDetail.EpisodeDetailActivity
import com.squareup.picasso.Picasso

class ListEpisodesAdapter(
        private val episodes: List<Episode>
        ): RecyclerView.Adapter<ListEpisodesAdapter.ListEpisodesViewHolder>() {

    class ListEpisodesViewHolder(private val binding: EpisodeItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        private lateinit var episode: Episode

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION && ::episode.isInitialized ) {
                    val selctedEpisode: Episode =  episode
                    val intent = Intent(it.context, EpisodeDetailActivity::class.java)
                    intent.putExtra(EPISODE_ID, selctedEpisode.id)
                    it.context.startActivity(intent)
                }
            }
        }

        fun bind(episode: Episode) {
            this.episode = episode
            try {
                if(episode.image.original != ""){
                    Picasso.get().load(episode.image.original)
                            .fit()
                            .into(binding.episodeImage);
                } else if (episode.image.medium != ""){
                    Picasso.get().load(episode.image.medium)
                            .fit()
                            .into(binding.episodeImage)
                }
            }
            catch (ex: Exception){

            }

            episode.summary = if(episode.summary != "" && episode.summary != null ) Html.fromHtml(episode.summary).toString() else ""
            binding.episode = episode
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ListEpisodesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EpisodeItemBinding.inflate(layoutInflater, parent, false)

                return ListEpisodesViewHolder(binding)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEpisodesViewHolder {
        return ListEpisodesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListEpisodesViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }
}