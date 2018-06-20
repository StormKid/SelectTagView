package com.stormkid.selecttagview.util

import java.io.Serializable

/**
根据需要实现接口回调组成bean
@author ke_li
@date 2018/6/20
 */
interface SupportEntity:Serializable {
    var id: String
    var name: String
    var isChoose: Boolean
}