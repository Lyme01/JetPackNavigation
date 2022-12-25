package com.exa.base.bean



class TitleBean(
    val title:String="333",
    val leftImage: Int? =null ,
    val rightImage:Int?=null,
    val rightText: String="",
    val hasLeft:Boolean=false,
    val leftClick:()->Unit={},
    val rightClick:()->Unit={},
)