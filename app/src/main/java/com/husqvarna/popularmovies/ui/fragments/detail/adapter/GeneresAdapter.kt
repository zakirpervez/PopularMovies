package com.husqvarna.popularmovies.ui.fragments.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.husqvarna.popularmovies.api.models.response.GenresItem
import com.husqvarna.popularmovies.databinding.GenereItemBinding

/**
 * Adapter class for the generes list.
 */
class GeneresAdapter : RecyclerView.Adapter<GeneresAdapter.GeneresViewHolder>() {

    private val generesList = mutableListOf<GenresItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneresViewHolder {
        val binding = GenereItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GeneresViewHolder(binding)
    }

    override fun getItemCount(): Int = generesList.size

    override fun onBindViewHolder(holder: GeneresViewHolder, position: Int) {
        val generesItem = generesList[position]
        holder.binding.generesItem = generesItem
    }

    /**
     * Update the generes list.
     * @param generes The [List] of generes.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateGeneres(generes: List<GenresItem?>) {
        if (generesList.isNotEmpty()) {
            generesList.clear()
        }
        generesList.addAll(generes)
        notifyDataSetChanged()
    }

    /**
     * View holder class for the generes list.
     * @param binding The [GenereItemBinding] binding.
     */
    class GeneresViewHolder(val binding: GenereItemBinding) : RecyclerView.ViewHolder(binding.root)
}
