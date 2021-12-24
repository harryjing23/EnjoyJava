package com.harry.kotlin.home

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView

/**
 * Created on 2021/12/16.
 * @author harry
 */

// 解决ListView在ScrollView中嵌套，显示不全的问题
// 构造函数的后面两个参数可以允许为空，且有默认参数值。用Kotlin将需要override的三个构造函数合并为一个
// 当运用了Kotlin特性，为了让Java调用时也生效，要加上@JvmOverloads。这里避免注解与后面的主构造混淆，加上了constructor
class CustomListView @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    ListView(context, attrs, defStyleAttr) {

    // 重新测量高度
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        // Java的写法
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        // Java中的移位<<和>>，在Kotlin中用shl()和shr()代替
        val expendSpec = MeasureSpec.makeMeasureSpec(
            Integer.MAX_VALUE shr (2),
            MeasureSpec.AT_MOST
        )

        // 传入重新测量的高度，解决了显示不全的问题
        super.onMeasure(widthMeasureSpec, expendSpec)
    }
}