package com.example.starwarsapi.presentation.starships

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.databinding.ItemStarShipsBinding
import com.example.starwarsapi.domain.common.base.BaseDiffCallBack
import com.example.starwarsapi.domain.starships.entity.StarShipsEntity


class StarShipsAdapter :
    ListAdapter<StarShipsEntity, StarShipsAdapter.StarShipsViewHolder>(BaseDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarShipsViewHolder {
        return StarShipsViewHolder(
            ItemStarShipsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StarShipsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class StarShipsViewHolder(private val binding: ItemStarShipsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(starShips: StarShipsEntity) {
            binding.tvName.text = starShips.name
        }
    }
}