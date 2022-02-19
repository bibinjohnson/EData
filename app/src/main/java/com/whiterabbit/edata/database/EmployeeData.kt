package com.whiterabbit.edata.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "employeeTable")
@Parcelize
data class EmployeeData(
    val name: String? = null,
    val image: String? = null,
    val company: String? = null,
    @PrimaryKey val email: String,
    val website: String? = null,
): Parcelable
