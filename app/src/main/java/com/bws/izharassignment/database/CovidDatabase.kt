package com.bws.izharassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cases::class, State::class, Tested::class], version = 1)
abstract class CovidDatabase : RoomDatabase() {
    abstract fun covidDAO(): CovidDAO


    companion object {

        @Volatile
        private var INSTANCE: CovidDatabase? = null

        fun getDatabase(context: Context): CovidDatabase {

            if (INSTANCE == null) {

                synchronized(this){
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            CovidDatabase::class.java,
                            "covidDB"
                        ).build()
                }
            }
            return INSTANCE!!
        }
    }
}