package com.harry.kotlin.database

import androidx.room.*

/**
 * Created on 2021/12/9.
 * @author harry
 */


@Dao
interface StudentDao {

    // 增
    @Insert
    fun insertStudents(vararg student: Student)

    // 删
    @Query("DELETE FROM student")
    fun deleteAllStudents()

    // 条件删除
    @Delete
    fun deleteStudents(vararg student: Student)

    // 改
    @Update
    fun updateStudents(vararg student: Student)

    // 查
    @Query("SELECT * FROM student ORDER BY ID DESC")
    fun queryAllStudents(): List<Student>
}