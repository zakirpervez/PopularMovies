package com.husqvarna.popularmovies.di

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
}
