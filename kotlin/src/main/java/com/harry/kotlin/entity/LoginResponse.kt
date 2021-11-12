package com.harry.kotlin.entity

/**
 * Created on 2021/11/12.
 * @author harry
 */

// Java中的泛型通配符为?。Kotlin中用*
data class LoginResponse(
    val admin: Boolean,
    val chapterTops: List<*>,
    val collectIds: List<*>,
    val email: String?,
    val icon: String?,
    val id: String?,
    val nickname: String?,
    val password: String?,
    val publicName: String?,
    val token: String?,
    val type: Int,
    val username: String?
)