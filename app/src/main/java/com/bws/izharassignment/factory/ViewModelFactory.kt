package com.bws.izharassignment.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bws.izharassignment.repository.Repository
import com.bws.izharassignment.ui.CovidViewModel

class ViewModelFactory(private val repository: Repository, val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CovidViewModel::class.java)) {
            return CovidViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}