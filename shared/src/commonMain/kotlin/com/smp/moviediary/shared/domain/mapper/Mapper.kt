package com.smp.moviediary.shared.domain.mapper

interface Mapper<From, To> {
    fun map(data: From): To
    fun mapAll(data: List<From>): List<To> = data.map(::map)
    fun reverse(data: To): From
    fun reverseAll(data: List<To>): List<From> = data.map(::reverse)
} 