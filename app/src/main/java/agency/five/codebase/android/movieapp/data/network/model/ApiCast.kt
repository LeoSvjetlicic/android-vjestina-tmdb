package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCast(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("character")
    val character: String,
    @SerialName("profile_path")
    val profilePath: String?
) {
    fun toActor() = Actor(
        id = id,
        name = name,
        character = character,
        imageUrl = if (profilePath != null) {
            "$BASE_IMAGE_URL/$profilePath"
        } else {
            "https://upload.wikimedia.org/wikipedia/commons/b/bc/Unknown_person.jpg"
        },
    )
}
