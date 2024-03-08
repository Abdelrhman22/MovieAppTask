package com.task.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.movieapp.data.model.MovieInfoEntity
import com.task.movieapp.databinding.CardItemBinding


class MoviesListAdapter(private val listener: (MovieInfoEntity) -> Unit) :
    ListAdapter<MovieInfoEntity, MoviesListAdapter.ViewHolder>(DIFF) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }


    inner class ViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindItem(item: MovieInfoEntity) {
            with(binding) {
                root.setOnClickListener {
                    listener(getItem(adapterPosition))
                }
                movieItem = item
            }
        }
    }

    companion object {

        val DIFF = object : DiffUtil.ItemCallback<MovieInfoEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieInfoEntity,
                newItem: MovieInfoEntity
            ): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: MovieInfoEntity,
                newItem: MovieInfoEntity
            ): Boolean =
                oldItem == newItem

        }


    }

}