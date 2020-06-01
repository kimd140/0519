package com.example.myapplication0528

import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="PEOPLE")
data class People(
    @PrimaryKey(autoGenerate = true) val id:Long,
    @ColumnInfo(name="NAME") @NotNull val name: String,
    @ColumnInfo(name = "PHONE") val phone: String ="",
    @ColumnInfo(name = "AGE") val age: Int = 0
)