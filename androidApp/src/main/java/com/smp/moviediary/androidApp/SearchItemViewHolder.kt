package com.smp.moviediary.androidApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.smp.moviediary.androidApp.databinding.FragmentSearchItemBinding
import com.smp.moviediary.androidApp.model.Movie

class SearchItemViewHolder(itemView: View, private val listener: SearchListAdapter.Listener) :
    RecyclerView.ViewHolder(itemView) {

    private var boundId: Long = -1L

    private val binding = FragmentSearchItemBinding.bind(itemView).apply {
        scrapButton.setOnClickListener {
            listener.onUpdateScrap(boundId, scrapButton.isActivated.not())
        }
    }

    fun bind(movie: Movie) {
        boundId = movie.id

        with(binding) {
            thumbView.load(movie.thumb) {
                crossfade(true)
                placeholder(R.drawable.ic_movie_placeholder)
            }

            titleView.text = movie.spannedTitle
            subtitleView.text = movie.spannedSubTitle ?: UNKNOWN
            directorView.text = movie.director ?: UNKNOWN
            actorsView.text = movie.actor ?: UNKNOWN
            publishedAtView.text = movie.publishedAt ?: UNKNOWN

            scrapButton.isActivated = movie.scrap
        }
    }

    companion object {
        const val UNKNOWN = "Unknown"

        fun create(parent: ViewGroup, listener: SearchListAdapter.Listener): SearchItemViewHolder {
            return SearchItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_search_item, parent, false),
                listener
            )
        }
    }

}