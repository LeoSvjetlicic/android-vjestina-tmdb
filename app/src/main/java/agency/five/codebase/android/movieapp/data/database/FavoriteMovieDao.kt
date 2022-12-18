package agency.five.codebase.android.movieapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favoriteMovies")
    fun favorites(): Flow<List<DbFavoriteMovie>>

    @Query("SELECT * FROM favoriteMovies WHERE id=:id")
    fun getMovieById(id: Int): Flow<DbFavoriteMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: DbFavoriteMovie)

    @Query("DELETE FROM favoriteMovies WHERE id=:id")
    fun deleteMovie(id: Int)
}
