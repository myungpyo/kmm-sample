package com.smp.moviediary.androidApp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smp.moviediary.androidApp.model.Movie

class SearchListAdapter(private val listener: Listener) :
    RecyclerView.Adapter<SearchItemViewHolder>() {

    private var movies = listOf<Movie>()

    fun swapData(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    interface Listener {
        fun onUpdateScrap(id: Long, scrap: Boolean)
    }
}