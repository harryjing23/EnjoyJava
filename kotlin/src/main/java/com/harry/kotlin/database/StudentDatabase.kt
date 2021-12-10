package com.harry.kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created on 2021/12/9.
 * @author harry
 */

// 注解中的数组：java entities={}， kotlin entities=[]
@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    // 返回给用户的是Dao，用于增删改查
    abstract fun getStudentDao(): StudentDao

    companion object {
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, StudentDatabase::class.java, "student.db")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE
        }


        fun getDatabase(): StudentDatabase? = INSTANCE
    }

}