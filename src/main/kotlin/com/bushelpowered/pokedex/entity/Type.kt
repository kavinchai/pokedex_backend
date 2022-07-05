package com.bushelpowered.pokedex.entity

import com.bushelpowered.pokedex.dto.TypeResponse
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "type_table")
data class Type(
    @Id
    @Column(name = "type_id")
    val id: Int,

    @Column(name = "type")
    val type: String,
){
    fun Type.toResponse(): TypeResponse {
        return TypeResponse(
            type = this.type
        )
    }
}