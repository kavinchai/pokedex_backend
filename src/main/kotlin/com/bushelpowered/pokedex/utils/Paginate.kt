package com.bushelpowered.pokedex.utils

import com.bushelpowered.pokedex.dto.PokemonResponse
import org.springframework.beans.support.PagedListHolder
import org.springframework.data.domain.PageImpl

fun paginate(pageNum: Int, pageSize: Int, responseList: List<PokemonResponse>): PageImpl<PokemonResponse> {
    val typePages = PagedListHolder(responseList)
    typePages.page = pageNum
    typePages.pageSize = pageSize
    return PageImpl(typePages.pageList)
}