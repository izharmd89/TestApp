package com.bws.izharassignment.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.izharassignment.utils.NetworkUtils
import com.bws.izharassignment.utils.Resources
import com.bws.izharassignment.R
import com.bws.izharassignment.repository.Repository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CovidViewModel(val repository: Repository, val context: Context) : ViewModel() {

    var responseLiveData: MutableLiveData<Resources<String>> = MutableLiveData()

    fun callDataAPI() = viewModelScope.launch {

        if (NetworkUtils.isNetworkAvailable(context)) {
            responseLiveData.postValue(
                Resources.Loading(
                    loadingMessage = context.resources.getString(
                        R.string.LOADING_PLEASE_WAIT
                    )
                )
            )
            val call: Call<ResponseBody> = repository.callMemesApi()
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val res = response.body()!!.string()
                    responseLiveData.postValue(Resources.Success(res))
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    responseLiveData.postValue(Resources.Error(errorMessage = "Error"))
                }
            })

        } else {
            responseLiveData.postValue(Resources.NoInternet(context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
        }
    }

}