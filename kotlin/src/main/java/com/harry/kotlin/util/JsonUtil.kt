package com.harry.kotlin.util

/**
 * Created on 2021/11/10.
 * @author harry
 */

// 工具类用object
object JsonUtil {

//    fun formatJson(jsonStr: String?): String {
//    }

    private fun addIndentBlank(sb: StringBuilder, indent: Int) {
        for (i in 0 until indent) {
            sb.append('\t')
        }
    }
}