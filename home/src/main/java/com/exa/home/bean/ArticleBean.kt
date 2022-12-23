package com.exa.home.bean

import com.exa.base.bean.BaseResponse
import kotlinx.coroutines.flow.Flow

/**
 * @author wwq
 * @description:
 * @date :2022/3/24
 */

data class ArticleBean(
    val curPage: Int=0,
    val datas: List<DataX>?=null,
    val offset: Int=0,
    val over: Boolean=false,
    val pageCount: Int=0,
    val size: Int=0,
    val total: Int=0
) {
    data class DataX (
        val apkLink: String,
        val audit: Int,
        val author: String,
        val canEdit: Boolean,
        val chapterId: Int,
        val chapterName: String,
        var collect: Boolean,
        val courseId: Int,
        val desc: String,
        val descMd: String,
        val envelopePic: String,
        val fresh: Boolean,
        val host: String,
        val id: Int,
        val link: String,
        val niceDate: String,
        val niceShareDate: String,
        val origin: String,
        val prefix: String,
        val projectLink: String,
        val publishTime: Long,
        val realSuperChapterId: Int,
        val selfVisible: Int,
        val shareDate: Long,
        val shareUser: String,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Tag>,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
    )
}



data class Tag(
    val name: String,
    val url: String
)