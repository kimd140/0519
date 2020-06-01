package com.example.myapplication0528

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [People::class],version=1)
abstract class RoomDatabase : RoomDatabase(){
    abstract fun peopleDao():PeopleDAO

    companion object{
        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase?{
            if(instance==null){
                instance = Room.databaseBuilder(context.applicationContext,
                    RoomDatabase::class.java, "weknot_database")
                    .fallbackToDestructiveMigration()
//                     .allowMainThreadQueries()
                    .build()

            }
            return instance
        }
    }



    
    
    
}