package com.husqvarna.popularmovies.ui.fragments.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.api.models.response.ProductionCompaniesItem
import com.husqvarna.popularmovies.databinding.ProductionHousesItemBinding
import com.husqvarna.popularmovies.util.loadImage
import java.util.Locale

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
        productionCompaniesItem?.originCountry.let { countryCode->
            holder.binding.countryText.text = Locale(countryCode).displayCountry
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateProductionCompanies(productionCompanies: List<ProductionCompaniesItem?>) {
        if (productionCompaniesList.isNotEmpty()) {
            productionCompaniesList.clear()
        }
        productionCompaniesList.addAll(productionCompanies)
        notifyDataSetChanged()
    }

    class ProductionCompaniesViewHolder(val binding: ProductionHousesItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
