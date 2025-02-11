package dev.sanskar.featuretesteduco.feature_course.data.remote.dto

import dev.sanskar.featuretesteduco.core.domain.models.Comment
import java.text.SimpleDateFormat
import java.util.*

@kotlinx.serialization.Serializable
data class CommentDto(
    val id: String,
    val username: String,
    val userType: String,
    val profilePictureUrl: String,
    val timestamp: Long,
    val comment: String,
    val isLiked: Boolean,
    val likeCount: Int
) {
    fun toComment(): Comment {
        return Comment(
            id = id,
            username = username,
            userType = userType,
            profilePictureUrl = profilePictureUrl,
            formattedTime = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).run {
                format(timestamp)
            },
            comment = comment,
            isLiked = isLiked,
            likeCount = likeCount

        )
    }
}