package com.bushelpowered.pokedex.utils

import org.springframework.beans.support.PagedListHolder
import org.springframework.data.domain.PageImpl

inline fun <reified T> List<T>.toPaginatedResponse(pageNum: Int, pageSize: Int): PageImpl<T>{
    val page = PagedListHolder(this)
    page.page = pageNum
    page.pageSize = pageSize
    return PageImpl(page.pageList)
}
