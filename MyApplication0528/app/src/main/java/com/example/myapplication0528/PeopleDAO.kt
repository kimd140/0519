package com.example.myapplication0528

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeopleDAO {
    @Query( "SELECT*FROM PEOPLE")
    fun getAll():List<People>

    @Query("SELECT*FROM PEOPLE WHERE NAME IN (:peopleName)")
    fun getPeople(peopleName:Array<String>): List<People>

    @Insert
    fun  insert(vararg people: People)

    @Insert
    fun insertAll(vararg peoples:People)

    @Delete
    fun delete(peoples: People)
}

