package dev.sanskar.featuretesteduco.feature_auth.data.remote

import dev.sanskar.featuretesteduco.core.data.dto.response.BasicApiResponse
import dev.sanskar.featuretesteduco.feature_auth.data.remote.request.CreateAccountRequest
import dev.sanskar.featuretesteduco.feature_auth.data.remote.request.GenerateOtpRequest
import dev.sanskar.featuretesteduco.feature_auth.data.remote.request.ResetPasswordRequest
import dev.sanskar.featuretesteduco.feature_auth.data.remote.request.SignInRequest
import dev.sanskar.featuretesteduco.feature_auth.data.remote.response.AuthResponse

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/user/create")
    suspend fun signUp(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<Unit>


    @POST("/api/user/login")
    suspend fun signIn(
        @Body request: SignInRequest
    ): BasicApiResponse<AuthResponse>

    @POST("/api/user/reset_password")
    suspend fun resetPassword(
        @Body request: ResetPasswordRequest
    ): BasicApiResponse<Unit>

    @GET("/api/user/authenticate")
    suspend fun authenticate()

    companion object {
        const val BASE_URL = "http://192.168.0.120:8082"
    }
}