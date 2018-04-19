package com.stormkid.selecttagview

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stormkid.selecttagview.util.DimenUtil
import java.io.Serializable

/**
 * Created by ke_li on 2018/2/28.
 */
class SelectTagView : ViewGroup {

    private var textSize = 0 // tag字体大小

    private var textColor = 0 // tag字体颜色
    private var textSelectColor = 0 // tag字体选择颜色
    private var backGroundSelectRes = R.drawable.shape_selected

    private var backGroundNoSelectRes = R.drawable.shape_no_select
    private var type = NORMAL_CLOUD_TYPE

    companion object {
        const val NORMAL_CLOUD_TYPE = "NORMAL_CLOUD_TYPE" // 普通的操作方式
        const val SINGLE_SELECT_TYPE = "SINGLE_SELECT_TYPE" // 单选
        const val MORE_SELECT_TYPE = "MORE_SELECT_TYPE" // 多选
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    /**
     * init对象
     */
    constructor(context: Context, attrs: AttributeSet?, def: Int) : super(context, attrs, def) {
        val attr = context.obtainStyledAttributes(attrs!!, R.styleable.SelectTagView, def, 0)
        textSize = attr.getInt(R.styleable.SelectTagView_tagTextSize, 8)
        textColor = attr.getResourceId(R.styleable.SelectTagView_tagTextColor, R.color.text_666)
        backGroundNoSelectRes = attr.getResourceId(R.styleable.SelectTagView_tagBgNoSelectRes, R.drawable.shape_no_select)
        backGroundSelectRes = attr.getResourceId(R.styleable.SelectTagView_tagBgSelectRes, R.drawable.shape_selected)
        textSelectColor = attr.getResourceId(R.styleable.SelectTagView_tagTextSelectColor, textColor)
        val xmlType = attr.getInt(R.styleable.SelectTagView_tagSelectType, 0)
        when (xmlType) {
            0 -> type = NORMAL_CLOUD_TYPE
            1 -> type = SINGLE_SELECT_TYPE
            2 -> type = MORE_SELECT_TYPE
        }
    }

    /**
     * 使用系统自带margin params
     */
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }


    /**
     * 更新type是否为多选或单选或者只点击
     */
    fun setType(type: String) {
        this.type = type
    }

    /**
     * 控制子view所在的位置
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // 初始化子控件位置
        var pointWidth = 0
        var pointHeight = 0

        (0 until childCount)
                .asSequence() //序列化
                .map { getChildAt(it) }//开始遍历子控件
                .filter { it.visibility != View.GONE } // 过滤view为gone的控件
                .forEach {
                    // 获取子控件的测量宽高
                    val childHeight = it.measuredHeight
                    val childWidth = it.measuredWidth
                    // 使用margin的params作为定位器
                    val layoutParams = it.layoutParams as MarginLayoutParams
                    // 判断是否换行。
                    if (pointWidth + childWidth + layoutParams.leftMargin + layoutParams.rightMargin > width) {
                        pointHeight += childHeight + layoutParams.topMargin + layoutParams.bottomMargin
                        pointWidth = 0
                    }
                    // 计算控件绘图定位
                    val top = layoutParams.topMargin + pointHeight
                    val bottom = layoutParams.bottomMargin + pointHeight + childHeight
                    val left = layoutParams.leftMargin + pointWidth
                    val right = layoutParams.rightMargin + pointWidth + childWidth
                    it.layout(left, top, right, bottom)
                    // 通过view的tag来显示view的实际变化
                    val view = it as TextView
                    val tag = it.tag as CateGroyBean
                    if (tag.isChoose) {
                        it.setBackgroundResource(backGroundSelectRes)
                        view.setTextColor(ContextCompat.getColor(context,textSelectColor))
                    } else {
                        it.setBackgroundResource(backGroundNoSelectRes)
                        view.setTextColor(ContextCompat.getColor(context,textColor))
                    }
                    //记录最终view的位置
                    pointWidth += layoutParams.leftMargin + childWidth + layoutParams.rightMargin
                }

    }


    /**
     * 计算子控件大小进行自动换行处理
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)

        //初始化父控件大小
        var resultWidth = 0
        var resultHeight = 0


        // 初始化行控件大小
        var itemWidth = 0
        var itemHeight = 0

        for (i in 0 until childCount) { // 遍历 所有的子元素
            val child = getChildAt(i)
            val layoutParams = child.layoutParams as MarginLayoutParams
            measureChild(child, widthMeasureSpec, heightMeasureSpec) // 先测量

            //计算所有的子控件宽高
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            // 通过margin计算所有子控件实际加上margin值的大小
            val realWidth = childWidth + layoutParams.leftMargin + layoutParams.rightMargin
            val realHeight = childHeight + layoutParams.topMargin + layoutParams.bottomMargin

            if (sizeWidth < (itemWidth + realWidth)) {//换行
                resultWidth = Math.max(realWidth, itemWidth)
                resultHeight += realHeight
                itemHeight = realHeight
                itemWidth = realWidth
            } else { // 添加
                itemWidth += realWidth
                itemHeight = Math.max(realHeight, itemHeight)
            }

            // 最后一行不换行
            if (i == childCount - 1) {
                resultWidth = Math.max(realWidth, itemWidth)
                resultHeight += itemHeight
            }
            // 通过判断本自定义控件width||height 的属性是否为warp_content来进行给此view赋值，如果为march_parent或者其他属性，则使用其他属性定义的宽高值
            // 如果仅为wrap_content则使用计算后的宽高给父控件赋值
            setMeasuredDimension(if (modeWidth == View.MeasureSpec.EXACTLY) sizeWidth else resultWidth,
                    if (modeHeight == View.MeasureSpec.EXACTLY) sizeHeight else resultHeight)
        }
    }

    fun initChild(list: MutableList<CateGroyBean>, callbcak: (MutableList<CateGroyBean>) -> Unit) = list.apply {
        removeAllViews()
    }
            .forEachIndexed { position, categoryBean ->
                val textView = TextView(context)
                textView.setTextColor(ContextCompat.getColor(context, textColor))
                DimenUtil.getInstance(context).getSmallSize(textView)
                textView.tag = categoryBean
                val layoutParams = MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT)
                val margin = DimenUtil.getInstance(context).getWindowWidth() / 45
                layoutParams.rightMargin = margin
                layoutParams.leftMargin = margin
                layoutParams.bottomMargin = margin
                layoutParams.topMargin = margin
                textView.layoutParams = layoutParams
                textView.text = categoryBean.name
                textView.setBackgroundResource(backGroundNoSelectRes)
                textView.setPadding(margin * 2, margin, margin * 2, margin)
                textView.setOnClickListener {
                    //通过数据绑定来控制显示
                    when (type) {
                        SINGLE_SELECT_TYPE -> { //单选
                            categoryBean.isChoose = !categoryBean.isChoose
                            it.tag = categoryBean
                            list.forEachIndexed { index, categaryBean ->
                                if (position != index) {
                                    categaryBean.isChoose = false
                                    getChildAt(index).tag = categaryBean
                                }
                            }
                        }
                        MORE_SELECT_TYPE -> { // 多选
                            categoryBean.isChoose = !categoryBean.isChoose
                            it.tag = categoryBean
                        }
                        NORMAL_CLOUD_TYPE -> { //普通选定
                            categoryBean.isChoose = true
                            it.tag = categoryBean
                            list.forEachIndexed { index, categaryBean ->
                                if (position != index) {
                                    categaryBean.isChoose = false
                                    getChildAt(index).tag = categaryBean
                                }
                            }
                        }
                    }
                    requestLayout()
                    callbcak.invoke(list)
                }
                addView(textView)
            }

    data class CateGroyBean(val id: String, val name: String, var isChoose: Boolean):Serializable
}