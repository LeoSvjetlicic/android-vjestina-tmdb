package agency.five.codebase.android.movieapp.data.di

import agency.five.codebase.android.movieapp.data.database.FavoriteMovieDao
import agency.five.codebase.android.movieapp.data.database.MovieAppDatabase
import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get(), get(), Dispatchers.IO)
    }

    fun provideFavoriteMovieDao(db: MovieAppDatabase): FavoriteMovieDao = db.getMovieDao()
    single {
        provideFavoriteMovieDao(get())
    }
}

