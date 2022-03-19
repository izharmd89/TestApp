package com.bws.izharassignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Cases")
data class Cases(
    @PrimaryKey(autoGenerate = true)
    val id :Long,
    val dailyconfirmed: String,
    val dailydeceased: String,
    val dailyrecovered: String,
    val date: String,
    val dateymd: String,
    val totalconfirmed: String,
    val totaldeceased: String,
    val totalrecovered: String

)
