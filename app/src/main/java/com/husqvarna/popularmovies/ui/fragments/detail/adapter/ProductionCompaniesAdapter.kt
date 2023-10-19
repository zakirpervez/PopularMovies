package com.husqvarna.popularmovies.ui.fragments.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.api.models.response.ProductionCompaniesItem
import com.husqvarna.popularmovies.databinding.ProductionHousesItemBinding
import com.husqvarna.popularmovies.util.loadImage
import java.util.*

/**
 * Adapter class for the production companies list.
 */
class ProductionCompaniesAdapter :
    RecyclerView.Adapter<ProductionCompaniesAdapter.ProductionCompaniesViewHolder>() {

    private val productionCompaniesList = mutableListOf<ProductionCompaniesItem?>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProductionCompaniesViewHolder {
        val binding =
            ProductionHousesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductionCompaniesViewHolder(binding)
    }

    override fun getItemCount(): Int = productionCompaniesList.size

    override fun onBindViewHolder(holder: ProductionCompaniesViewHolder, position: Int) {
        val productionCompaniesItem = productionCompaniesList[position]
        holder.binding.productionCompaniesItem = productionCompaniesItem
        val posterUrl = "${BuildConfig.IMAGES_URL}${productionCompaniesItem?.logoPath}"
        holder.binding.productionHouseImage.loadImage(posterUrl)
        productionCompaniesItem?.originCountry?.let { countryCode ->
            holder.binding.countryText.text = Locale(countryCode).displayCountry
        }
    }

    /**
     * Update the production companies list.
     * @param productionCompanies The [List] of production companies.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateProductionCompanies(productionCompanies: List<ProductionCompaniesItem?>) {
        if (productionCompaniesList.isNotEmpty()) {
            productionCompaniesList.clear()
        }
        productionCompaniesList.addAll(productionCompanies)
        notifyDataSetChanged()
    }

    /**
     * View holder class for the production companies list.
     * @param binding The [ProductionHousesItemBinding] binding.
     */
    class ProductionCompaniesViewHolder(val binding: ProductionHousesItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
