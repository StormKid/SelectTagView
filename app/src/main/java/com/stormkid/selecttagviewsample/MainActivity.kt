package com.stormkid.selecttagviewsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.stormkid.selecttagview.SelectTagView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTitle()
    }


    private fun initTitle() {
        val list = arrayListOf(
                SelectTagView.CateGroyBean("1", "普通选定", true),
                SelectTagView.CateGroyBean("2", "单选", false),
                SelectTagView.CateGroyBean("3", "多选", false)
        )


        val value = arrayListOf(
                SelectTagView.CateGroyBean("1", "java", false),
                SelectTagView.CateGroyBean("2", "python", false),
                SelectTagView.CateGroyBean("3", "c++", false),
                SelectTagView.CateGroyBean("4", "kotlin", false),
                SelectTagView.CateGroyBean("5", "c", false),
                SelectTagView.CateGroyBean("6", "c#", false),
                SelectTagView.CateGroyBean("7", "php", false),
                SelectTagView.CateGroyBean("8", "javascript", false),
                SelectTagView.CateGroyBean("9", "typescript", false),
                SelectTagView.CateGroyBean("10", "html", false),
                SelectTagView.CateGroyBean("11", "css", false),
                SelectTagView.CateGroyBean("12", "dust", false)
        )
        value_list.initChild(value,{})


        choose.initChild(list, {
            it.forEach {
                if (it.isChoose){
                    when (it.id) {
                        "1" -> value_list.setType(SelectTagView.NORMAL_CLOUD_TYPE)
                        "2" -> value_list.setType(SelectTagView.SINGLE_SELECT_TYPE)
                        "3" -> value_list.setType(SelectTagView.MORE_SELECT_TYPE)
                    }
                }
            }
            value_list.initChild(value,{})
        })


    }
}
