package com.bws.izharassignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "State")
data class State(
    @PrimaryKey(autoGenerate = true)
    val id :Long,
    val active: String,
    val confirmed: String,
    val deaths: String,
    val deltaconfirmed: String,
    val deltadeaths: String,
    val deltarecovered: String,
    val lastupdatedtime: String,
    val migratedother: String,
    val recovered: String,
    val state: String,
    val statecode: String,
    val statenotes: String
)
