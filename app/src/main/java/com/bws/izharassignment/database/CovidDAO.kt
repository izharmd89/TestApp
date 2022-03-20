package com.bws.izharassignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CovidDAO {

    //FOR CASES DATA================
    @Insert
    suspend fun insertCases(cases: Cases)

    @Query("DELETE FROM cases")
    suspend fun deleteCase()

    @Query("SELECT * FROM cases")
    fun getCases(): LiveData<List<Cases>>

    //  FOR STATE DATA===================
    @Insert
    suspend fun insertState(state: State)

    @Query("DELETE FROM state")
    suspend fun deleteState()

    @Query("SELECT * FROM state")
    fun getState(): LiveData<List<State>>


    //  FOR TESTED DATA===================
    @Insert
    suspend fun insertTested(tested: Tested)

    @Query("DELETE FROM tested")
    suspend fun deleteTested()

    @Query("SELECT * FROM tested")
    fun getTested(): LiveData<List<Tested>>
}