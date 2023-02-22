package com.example.movieapp.di

import android.app.Application
import com.example.movieapp.application.TvmManiaApplication
import com.example.movieapp.feature_tv_show.data.remote.api.Api
import com.example.movieapp.feature_tv_show.data.repository.TvShowRepositoryImp
import com.example.movieapp.feature_tv_show.domain.repository.TvShowRepository
import com.example.movieapp.feature_tv_show.domain.use_case.GetTvShowDetails
import com.example.movieapp.feature_tv_show.domain.use_case.GetTvShows
import com.example.movieapp.feature_tv_show.domain.use_case.TvShowUseCases
import com.example.movieapp.util.ApiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesTvManiaApplication(
        @ApplicationContext app: Application
    ): TvmManiaApplication {
        return (app as TvmManiaApplication)
    }

    @Singleton
    @Provides
    fun providesApi(apiBuilder: ApiBuilder): Api {
        return apiBuilder.builder(api = Api::class.java)
    }

    @Singleton
    @Provides
    fun providesTvShowRepository(api: Api): TvShowRepository {
        return TvShowRepositoryImp(api = api)
    }

    @Singleton
    @Provides
    fun providesTvShowUseCases(repository: TvShowRepository): TvShowUseCases {
        return TvShowUseCases(
            getTvShows = GetTvShows(repository),
            getTvShowDetails = GetTvShowDetails(repository)
        )
    }
}