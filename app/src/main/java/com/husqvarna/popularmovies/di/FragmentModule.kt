package com.husqvarna.popularmovies.di

import com.husqvarna.popularmovies.ui.fragments.detail.adapter.GeneresAdapter
import com.husqvarna.popularmovies.ui.fragments.detail.adapter.ProductionCompaniesAdapter
import com.husqvarna.popularmovies.ui.fragments.home.adapter.MoviesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {
    @Provides
    fun provideMoviesAdapter(): MoviesAdapter {
        return MoviesAdapter()
    }

    @Provides
    fun provideGeneresAdapter(): GeneresAdapter {
        return GeneresAdapter()
    }

    @Provides
    fun provideProductionCompaniesAdapter(): ProductionCompaniesAdapter {
        return ProductionCompaniesAdapter()
    }
}
