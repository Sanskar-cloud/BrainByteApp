package dev.sanskar.featuretesteduco.feature_course.domain.use_case

import dev.sanskar.featuretesteduco.core.domain.models.CateforyOverView
import dev.sanskar.featuretesteduco.core.domain.models.Category
import dev.sanskar.featuretesteduco.core.util.Constants
import dev.sanskar.featuretesteduco.core.util.Resource
import dev.sanskar.featuretesteduco.feature_course.domain.repository.CourseRepository
import javax.inject.Inject

class GetPopularCategoriesUseCase @Inject constructor(
    val repository: CourseRepository
) {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): Resource<List<CateforyOverView>> {
        return repository.getPopularCategories(page, pageSize)
    }
}