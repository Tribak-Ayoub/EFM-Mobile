package com.example.viewmodelcounterapp

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("demande-inscri")
    suspend fun getInscrites(): List<Inscrite>

    @POST("demande-inscri")
    suspend fun createInscrite(@Body inscrite: Inscrite): Inscrite

    @DELETE("demande-inscri/{id}")
    suspend fun deleteInscrite(@Path("id") id: Int): Response<Unit>

    @PUT("demande-inscri/{id}")
    suspend fun updateInscrite(@Path("id") id: Int, @Body inscrite: Inscrite): Response<Unit>
}