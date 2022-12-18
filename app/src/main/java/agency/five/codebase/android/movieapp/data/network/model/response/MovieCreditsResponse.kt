package agency.five.codebase.android.movieapp.data.network.model.response

import agency.five.codebase.android.movieapp.data.network.model.ApiCast
import agency.five.codebase.android.movieapp.data.network.model.ApiCrew
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class   MovieCreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<ApiCast>,
    @SerialName("crew")
    val crew: List<ApiCrew>
)
