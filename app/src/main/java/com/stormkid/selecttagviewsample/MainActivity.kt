package com.stormkid.selecttagviewsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stormkid.selecttagview.SelectTagView
import com.stormkid.selecttagview.util.SupportEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTitle()
    }


    private fun initTitle() {
        val list = arrayListOf(
                CateGroyBean("1", "普通选定", true),
                CateGroyBean("2", "单选", false),
                CateGroyBean("3", "多选", false)
        )


        val value = arrayListOf(
                CateGroyBean("1", "java", false),
                CateGroyBean("2", "python", false),
                CateGroyBean("3", "c++", false),
                CateGroyBean("4", "kotlin", false),
                CateGroyBean("5", "c", false),
                CateGroyBean("6", "c#", false),
                CateGroyBean("7", "php", false),
                CateGroyBean("8", "javascript", false),
                CateGroyBean("9", "typescript", false),
                CateGroyBean("10", "html", false),
                CateGroyBean("11", "css", false),
                CateGroyBean("12", "dust", false)
        )
        value_list.initChild(value, {})


        choose.initChild(list) {
            it.forEach {
                if (it.isChoose) when (it.id) {
                    "1" -> value_list.setType(SelectTagView.NORMAL_CLOUD_TYPE)
                    "2" -> value_list.setType(SelectTagView.SINGLE_SELECT_TYPE)
                    "3" -> value_list.setType(SelectTagView.MORE_SELECT_TYPE)
                }
            }
            value_list.initChild(value, {})
        }


    }


    data class CateGroyBean(override var id: String, override var name: String, override var isChoose: Boolean) : SupportEntity
}
