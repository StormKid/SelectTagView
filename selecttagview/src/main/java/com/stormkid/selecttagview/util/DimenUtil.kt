package com.stormkid.selecttagview.util

import android.content.Context
import android.util.TypedValue
import android.widget.TextView

/**
 * Created by ke_li on 2017/12/4.
 */
class DimenUtil private constructor(context: Context) {

    private val myContext = context

    private val DEFAULT_SMALL_SIZE = 8

    private val DEFAULT_SIZE = 10

    private val DEFAULT_BIG_SIZE = 12

    private val DEFAULT_PADDING = 12

    private val DEFAULT_MARGIN = 12

    companion object {
        @JvmStatic
        fun getInstance(context: Context): DimenUtil {
            return DimenUtil.inner.util(context)
        }
    }

    private object inner {
        val util = fun(context: Context): DimenUtil = DimenUtil(context)
        val width = fun(context: Context): Int {
            val width = context.resources.displayMetrics.widthPixels
            val height = context.resources.displayMetrics.heightPixels
            return if (width<height) width else height}
    }


    fun getWindowWidth(): Int {
        return inner.width(myContext)
    }


    fun getResultSize(size: Int): Float {
        val window = getWindowWidth()
        val caculated = window * size / 250f
        return caculated
    }

    fun getDimen(size: Int):Int{
        return Math.round(getResultSize(size))
    }

    fun getDimen(size: Int,textView: TextView) =  Math.round(getResultSize(size)).apply {
        setTextSize(this,textView)
    }


    fun setTextSize(size: Int, textView: TextView) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResultSize(size))
    }



    fun getDefaltSize(textView: TextView) {
        setTextSize(DEFAULT_SIZE, textView)
    }

    fun getBigSize(textView: TextView) {
        setTextSize(DEFAULT_BIG_SIZE, textView)
    }

    fun getSmallSize(textView: TextView) {
        setTextSize(DEFAULT_SMALL_SIZE, textView)
    }

    fun getDefaltPadding(): Int {
        return Math.round(getResultSize(DEFAULT_PADDING))
    }

    fun getDefaltMarging(): Int {
        return Math.round(getResultSize(DEFAULT_MARGIN))
    }
}