package com.bws.izharassignment.repository

import com.bws.izharassignment.api.ApiInterface
import com.bws.izharassignment.network.RetrofitHelper

class Repository {
    val retrofitInstance = RetrofitHelper.getInstance().create(ApiInterface::class.java)
    fun callMemesApi() = retrofitInstance.getMemes()
}