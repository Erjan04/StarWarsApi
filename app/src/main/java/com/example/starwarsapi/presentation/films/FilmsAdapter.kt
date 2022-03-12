package com.example.starwarsapi.presentation.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.databinding.ItemFilmsBinding
import com.example.starwarsapi.domain.common.base.BaseDiffCallBack
import com.example.starwarsapi.domain.films.entity.FilmsEntity

class FilmsAdapter : ListAdapter<FilmsEntity, FilmsAdapter.FilmsViewHolder>(BaseDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return FilmsViewHolder(
            ItemFilmsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class FilmsViewHolder(private val binding: ItemFilmsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(films: FilmsEntity) {
            binding.tvName.text = films.title
        }

    }
}