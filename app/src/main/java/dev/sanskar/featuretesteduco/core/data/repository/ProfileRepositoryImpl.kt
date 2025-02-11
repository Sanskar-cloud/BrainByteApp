package dev.sanskar.featuretesteduco.core.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toFile

import dev.sanskar.featuretesteduco.R
import dev.sanskar.featuretesteduco.core.domain.models.CourseOverview
import dev.sanskar.featuretesteduco.core.domain.models.UserItem
import dev.sanskar.featuretesteduco.core.domain.repository.ProfileRepository
import dev.sanskar.featuretesteduco.core.domain.util.getFileName
import dev.sanskar.featuretesteduco.core.util.Constants
import dev.sanskar.featuretesteduco.core.util.Resource
import dev.sanskar.featuretesteduco.core.util.SimpleResource
import dev.sanskar.featuretesteduco.core.util.UiText
import dev.sanskar.featuretesteduco.feature_profile.data.remote.ProfileApi
import dev.sanskar.featuretesteduco.feature_profile.domain.model.Profile
import dev.sanskar.featuretesteduco.feature_profile.domain.model.ProfileHeader
import dev.sanskar.featuretesteduco.feature_profile.domain.model.UpdateProfileData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ProfileRepositoryImpl(
    private val profileApi: ProfileApi,
    private val sharedPreferences: SharedPreferences,
): ProfileRepository {
    override suspend fun getCoursesPaged(
        page: Int,
        pageSize: Int,
        userId: String
    ): Resource<List<CourseOverview>> {
        return try {
            val courses = profileApi.getCoursesPaged(
                userId = userId,
                page = page,
                pageSize = pageSize
            ).data
            Resource.Success(data = courses)
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun getUserInfos(page: Int, pageSize: Int): Resource<List<UserItem>> {
        return try {
            val response = profileApi.getUserInfos(page = page, pageSize = pageSize)
            Resource.Success(
                data = response.data
            )
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun getProfile(userId: String): Resource<Profile> {
        return try {
            val response = profileApi.getProfile(userId)
            if(response.successful) {
                Resource.Success(response.data)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun getProfileHeader(userId: String): Resource<ProfileHeader> {
        return try {
            val response = profileApi.getProfileHeader(userId)
            if(response.successful) {
                Resource.Success(response.data)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?,
        context: Context
    ): SimpleResource {


        val contentResolver = context.contentResolver
        val profilePictureFile: File? = profilePictureUri?.let {
            val fileName = contentResolver.getFileName(it)
            val file = File(context.cacheDir, fileName)
            try {
                val inputStream = contentResolver.openInputStream(it)
                val outputStream = FileOutputStream(file)
                inputStream?.copyTo(outputStream)
                outputStream.close()
                inputStream?.close()
                file
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

//        val profilePictureFile = Uri.fromFile(
//            File(
//                context.cacheDir,
//                profilePictureUri?.let { context.contentResolver.getFileName(it) }
//            )
//        ).toFile()

        val bannerFile: File? = bannerImageUri?.let {
            val fileName = contentResolver.getFileName(it)
            val file = File(context.cacheDir, fileName)
            try {
                val inputStream = contentResolver.openInputStream(it)
                val outputStream = FileOutputStream(file)
                inputStream?.copyTo(outputStream)
                outputStream.close()
                inputStream?.close()
                file
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
//        val bannerFile = Uri.fromFile(
//            File(
//                context.cacheDir,
//                bannerImageUri?.let { context.contentResolver.getFileName(it) }
//            )
//        ).toFile()
        println(bannerFile)
        println(profilePictureFile)


        return try {
            val response = profileApi.updateProfile(
                updateProfileData = MultipartBody.Part
                    .createFormData(
                        "update_profile_data",
                        Json.encodeToString(updateProfileData)
                    ),
                bannerImageUri = bannerFile?.let {
                    MultipartBody.Part
                        .createFormData(
                            "banner_image",
                            bannerFile.name,
                            bannerFile.asRequestBody()
                        )
                },
                profilePictureUri = profilePictureFile?.let {
                    MultipartBody.Part
                        .createFormData(
                            "profile_picture",
                            profilePictureFile.name,
                            profilePictureFile.asRequestBody()
                        )
                },

            )
            if(response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }

        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun searchUser(query: String): Resource<List<UserItem>> {
        return try {
            val response = profileApi.searchUser(query)
            Resource.Success(
                data = response.data
            )
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun followUser(followedUserId: String): SimpleResource {
        return try {
            val response = profileApi.followUser(
                followedUserId = followedUserId
            )
            if(response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun unfollowUser(followedUserId: String): SimpleResource {
        return try {
            val response = profileApi.unfollowUser(
                followedUserId = followedUserId
            )
            if(response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override fun logout() {
        sharedPreferences.edit()
            .putString(Constants.KEY_JWT_TOKEN, "")
            .putString(Constants.KEY_USER_ID, "")
            .apply()
    }
}