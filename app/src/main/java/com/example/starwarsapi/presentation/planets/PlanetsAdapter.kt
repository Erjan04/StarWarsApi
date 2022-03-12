package com.example.starwarsapi.presentation.planets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.databinding.ItemPlanetsBinding
import com.example.starwarsapi.domain.common.base.BaseDiffCallBack
import com.example.starwarsapi.domain.planets.entity.PlanetsEntity

class PlanetsAdapter :
    ListAdapter<PlanetsEntity, PlanetsAdapter.PlanetsViewHolder>(BaseDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetsViewHolder {
        return PlanetsViewHolder(
            ItemPlanetsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlanetsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class PlanetsViewHolder(private val binding: ItemPlanetsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(planets: PlanetsEntity) {
            binding.tvName.text = planets.name
        }
    }
}