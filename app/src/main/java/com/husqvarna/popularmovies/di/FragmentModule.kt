package com.husqvarna.popularmovies.di

import com.husqvarna.popularmovies.ui.fragments.detail.adapter.GeneresAdapter
import com.husqvarna.popularmovies.ui.fragments.detail.adapter.ProductionCompaniesAdapter
import com.husqvarna.popularmovies.ui.fragments.home.adapter.MoviesAdapter
import com.husqvarna.popularmovies.ui.fragments.home.paging.MoviesPagingAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


/**
 * Fragment module for providing the fragment level injection.
 */
@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {
    /**
     * Provide the movies adapter.
     * @return [MoviesAdapter]
     */
    @Provides
    fun provideMoviesAdapter(): MoviesAdapter {
        return MoviesAdapter()
    }

    /**
     * Provide the generes adapter.
     * @return [GeneresAdapter]
     */
    @Provides
    fun provideGeneresAdapter(): GeneresAdapter {
        return GeneresAdapter()
    }

    /**
     * Provide the production companies adapter.
     * @return [ProductionCompaniesAdapter]
     */
    @Provides
    fun provideProductionCompaniesAdapter(): ProductionCompaniesAdapter {
        return ProductionCompaniesAdapter()
    }

    /**
     * Provide the production companies adapter.
     * @return [ProductionCompaniesAdapter]
     */
    @Provides
    fun provideMoviesPagingAdapter(): MoviesPagingAdapter {
        return MoviesPagingAdapter()
    }
}
