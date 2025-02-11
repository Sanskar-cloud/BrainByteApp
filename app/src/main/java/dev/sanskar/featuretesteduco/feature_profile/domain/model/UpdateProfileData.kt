package dev.sanskar.featuretesteduco.feature_profile.domain.model

@kotlinx.serialization.Serializable
data class UpdateProfileData(
    val username: String,
    val bio: String? = null,
    val instagramUrl: String? = null,
    val facebookUrl: String? = null,
    val twitterUrl: String? = null
)
