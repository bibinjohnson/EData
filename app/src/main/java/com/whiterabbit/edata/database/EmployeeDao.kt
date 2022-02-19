package com.whiterabbit.edata.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {

    @Query("SELECT * from employeeTable")
    fun getAll(): List<EmployeeData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(homeData: EmployeeData)
}