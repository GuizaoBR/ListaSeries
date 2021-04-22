package com.guizaotech.listaseries.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.guizaotech.listaseries.DetailShowActivity
import com.guizaotech.listaseries.R
import com.guizaotech.listaseries.databinding.ShowItemBinding
import com.guizaotech.listaseries.model.Show
import com.squareup.picasso.Picasso

class PagingShowAdpater: PagedListAdapter<Show, PagingShowAdpater.ShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Show>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: Show,
                newConcert: Show
            ) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(
                oldConcert: Show,
                newConcert: Show
            ) = oldConcert.equals(newConcert)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val showListItemBinding: ShowItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.show_item, parent, false
        )
        return ShowViewHolder(showListItemBinding)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bindTo(getItem(position))

    }

    inner class ShowViewHolder(
        private val showItemBinding: ShowItemBinding
    ) : RecyclerView.ViewHolder(showItemBinding.root) {

        init {
            showItemBinding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selctedShow: Show? = getItem(position)
                    val intent = Intent(it.context, DetailShowActivity::class.java)
                    intent.putExtra("show", selctedShow!!.id)
                    it.context.startActivity(intent)
                }
            }

        }


        @SuppressLint("SetTextI18n")
        fun bindTo(show: Show?) {
            if (show != null) {
                showItemBinding.textViewTitulo.text = show.name
                showItemBinding.textViewGenres.text = show.genres.joinToString(", ")
                Picasso.get().load(show.image.original)
                    .fit()
                    .into(showItemBinding.showImage);

            } else {
                showItemBinding.textViewTitulo.text = "Carregando..."
            }
        }
    }

}