package dev.sanskar.featuretesteduco.feature_course.domain.use_case

import android.content.Context
import android.net.Uri
import dev.sanskar.featuretesteduco.core.domain.repository.ProfileRepository
import dev.sanskar.featuretesteduco.core.util.SimpleResource
import dev.sanskar.featuretesteduco.feature_course.data.remote.request.CreateCourseRequest
import dev.sanskar.featuretesteduco.feature_course.domain.repository.CourseRepository
import dev.sanskar.featuretesteduco.feature_profile.domain.model.UpdateProfileData
import javax.inject.Inject

class CreateCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
suspend operator fun invoke(
    createCourseRequest: CreateCourseRequest,
    thumbnailuri: Uri?,
    introvideouri: Uri?,
    context: Context
): SimpleResource {
    return courseRepository.createCourse(
        createCourseRequest,
        thumbnailuri,
        introvideouri,
        context
    )
}}