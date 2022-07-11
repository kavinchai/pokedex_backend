package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.PokemonResponse
import org.springframework.beans.support.PagedListHolder
import org.springframework.data.domain.PageImpl


/*
    Consider your parameters,
    does pageSize need to be passed, or can it be assumed from "list" size
    we also want to use "Receivers" often, so that we can reduce param list size


    fun T.xyz(), "T" is the receiver.
 */

fun paginate(pageNum: Int, pageSize: Int, responseList: List<PokemonResponse>): PageImpl<PokemonResponse> {
    val typePages = PagedListHolder(responseList)
    typePages.page = pageNum
    typePages.pageSize = pageSize
    return PageImpl(typePages.pageList)
}


// to make that cleaner, you could do something like this
// I'd encourage you to explore this until you fully understand it

inline fun <reified T> List<T>.toPaginatedResponse(pageNumber: Int): PageImpl<T> {
    val page = PagedListHolder(this)
    page.page = pageNumber
    page.pageSize = this.size
    return PageImpl(page.pageList)
}