package com.bws.izharassignment.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("data.json")
    fun getMemes(): Call<ResponseBody>
}