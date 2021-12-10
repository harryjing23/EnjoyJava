package com.harry.kotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created on 2021/12/8.
 * @author harry
 */

// ROOM分3个角色：Entity对应表, Dao对应增删改查, Database对应数据库

@Entity// ROOM的注解会在编译期生成代码
class Student() {


    @PrimaryKey(autoGenerate = true)// 主键。自动生成
    var id: Int = 0


    @ColumnInfo(name = "Student_name")// 指定字段的列信息。这里指定了列名，不加则以字段名为准
    lateinit var name: String

    @ColumnInfo(name = "Student_age")
    var age: Int = 0

    constructor(name: String, age: Int) : this() {
        this.name = name
        this.age = age
    }

    // Java的构造代码块在Kotlin中需要加init。Java的静态代码块要加static
    init {
    }

}