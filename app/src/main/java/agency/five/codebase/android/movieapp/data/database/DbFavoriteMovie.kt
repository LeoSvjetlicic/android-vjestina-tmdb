package agency.five.codebase.android.movieapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteMovies")
data class DbFavoriteMovie(
    @PrimaryKey val id: Int,
    val posterUrl: String
)
