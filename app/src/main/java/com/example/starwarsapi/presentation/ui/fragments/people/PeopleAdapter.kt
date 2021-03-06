package com.example.starwarsapi.presentation.ui.fragments.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsapi.databinding.ItemPeopleBinding
import com.example.starwarsapi.domain.common.base.BaseDiffCallBack
import com.example.starwarsapi.domain.people.entity.PeopleEntity

class PeopleAdapter :
    PagingDataAdapter<PeopleEntity, PeopleAdapter.PeopleViewHolder>(BaseDiffCallBack()) {

    var onItemClickListener: ((PeopleEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(
            ItemPeopleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class PeopleViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    onItemClickListener?.invoke(getItem(absoluteAdapterPosition)!!)
                }
            }
        }

        fun onBind(peopleEntity: PeopleEntity) {
            binding.tvName.text = peopleEntity.name
        }

    }
}